package com.sedsoftware.exchange.binance.repository

import com.sedsoftware.core.domain.repository.PairInfoRepository
import com.sedsoftware.core.domain.tools.Settings
import com.sedsoftware.exchange.binance.database.BinanceDatabase
import com.sedsoftware.exchange.binance.database.dao.BinanceSymbolsDao
import com.sedsoftware.exchange.binance.database.dao.BinanceSyncInfoDao
import com.sedsoftware.exchange.binance.mapper.BinanceSymbolsMapper
import com.sedsoftware.exchange.binance.network.BinanceApi
import javax.inject.Inject

class BinancePairInfoRepository @Inject constructor(
    private val api: BinanceApi,
    private val settings: Settings,
    private val db: BinanceDatabase,
    private val mapper: BinanceSymbolsMapper
) : PairInfoRepository {

    private val symbolsDao: BinanceSymbolsDao by lazy {
        db.getBinanceSymbolsDao()
    }

    private val syncInfoDao: BinanceSyncInfoDao by lazy {
        db.getBinanceSyncInfoDao()
    }

    override suspend fun downloadRemotePairsInfo() {
        val currencyPairs = api.getCurrencyPairs()
        symbolsDao.clearAll()
        symbolsDao.insert(mapper.mapSymbolsToDb(currencyPairs))
        syncInfoDao.insert(mapper.mapSyncInfoToDb(currencyPairs))
    }

    override suspend fun markAsDownloaded() {
        if (!settings.isAnyExchangeDownloaded) {
            settings.isAnyExchangeDownloaded = true
        }
    }
}
