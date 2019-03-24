package com.sedsoftware.exchange.binance.repository

import com.sedsoftware.exchange.binance.database.BinanceDatabase
import com.sedsoftware.exchange.binance.database.dao.BinanceSymbolsDao
import com.sedsoftware.exchange.binance.database.dao.BinanceSyncInfoDao
import com.sedsoftware.exchange.binance.mapper.BinanceSymbolsInfoMapper
import com.sedsoftware.exchange.binance.network.BinanceApi
import com.sedsoftware.exchange.binance.network.model.PairsInfo
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class PairsInfoRepository @Inject constructor(
    private val api: BinanceApi,
    private val db: BinanceDatabase,
    private val mapper: BinanceSymbolsInfoMapper
) {

    private val symbolsDao: BinanceSymbolsDao by lazy {
        db.getBinanceSymbolsDao()
    }

    private val syncInfoDao: BinanceSyncInfoDao by lazy {
        db.getBinanceSyncInfoDao()
    }

    fun getRemotePairsInfo(): Deferred<PairsInfo> =
        api.getCurrencyPairs()

    fun storePairsInfo(info: PairsInfo) {
        val symbols = mapper.mapSymbolsToDb(info)
        symbolsDao.insert(symbols)
    }

    fun storeSyncInfo(info: PairsInfo) {
        val syncInfo = mapper.mapSyncInfoToDb(info)
        syncInfoDao.insert(syncInfo)
    }
}
