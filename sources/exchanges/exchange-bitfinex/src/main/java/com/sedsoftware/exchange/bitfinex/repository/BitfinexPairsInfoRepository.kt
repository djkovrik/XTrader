package com.sedsoftware.exchange.bitfinex.repository

import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.exchange.bitfinex.database.BitfinexDatabase
import com.sedsoftware.exchange.bitfinex.database.dao.BitfinexSymbolsDao
import com.sedsoftware.exchange.bitfinex.database.dao.BitfinexSyncInfoDao
import com.sedsoftware.exchange.bitfinex.mapper.BitfinexSymbolsMapper
import com.sedsoftware.exchange.bitfinex.network.BitfinexApi
import javax.inject.Inject

class BitfinexPairsInfoRepository @Inject constructor(
    private val api: BitfinexApi,
    private val settings: Settings,
    private val db: BitfinexDatabase,
    private val mapper: BitfinexSymbolsMapper
) {

    private val symbolsDao: BitfinexSymbolsDao by lazy {
        db.getBitfinexSymbolsDao()
    }

    private val syncInfoDao: BitfinexSyncInfoDao by lazy {
        db.getBitfinexSyncInfoDao()
    }

    suspend fun getRemotePairsInfo(): List<String> =
        api.getCurrencyPairs()

    suspend fun storePairsInfo(info: List<String>) {
        symbolsDao.clearAll()
        symbolsDao.insert(mapper.mapSymbolsToDb(info))
    }

    suspend fun storeSyncInfo() {
        syncInfoDao.insert(mapper.makeSyncInfoDbModel())
    }

    fun markAsDownloaded() {
        if (!settings.isAnyExchangeDownloaded) {
            settings.isAnyExchangeDownloaded = true
        }
    }
}
