package com.sedsoftware.exchange.binance.repository

import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.exchange.binance.database.BinanceDatabase
import com.sedsoftware.exchange.binance.database.dao.BinanceSymbolsDao
import com.sedsoftware.exchange.binance.database.dao.BinanceSyncInfoDao
import com.sedsoftware.exchange.binance.mapper.BinanceSymbolsMapper
import com.sedsoftware.exchange.binance.network.BinanceApi
import com.sedsoftware.exchange.binance.network.model.PairsInfo
import javax.inject.Inject

class PairsInfoRepository @Inject constructor(
    private val api: BinanceApi,
    private val settings: Settings,
    private val db: BinanceDatabase,
    private val mapper: BinanceSymbolsMapper
) {

    private val symbolsDao: BinanceSymbolsDao by lazy {
        db.getBinanceSymbolsDao()
    }

    private val syncInfoDao: BinanceSyncInfoDao by lazy {
        db.getBinanceSyncInfoDao()
    }

    suspend fun getRemotePairsInfo(): PairsInfo =
        api.getCurrencyPairs()

    suspend fun storePairsInfo(info: PairsInfo) {
        symbolsDao.clearAll()
        symbolsDao.insert(mapper.mapSymbolsToDb(info))
    }

    suspend fun storeSyncInfo(info: PairsInfo) {
        syncInfoDao.insert(mapper.mapSyncInfoToDb(info))
    }

    fun markAsDownloaded() {
        if (!settings.isExchangesDownloaded) {
            settings.isExchangesDownloaded = true
        }
    }
}
