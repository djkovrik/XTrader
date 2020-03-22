package com.sedsoftware.exchange.bitfinex.repository

import com.sedsoftware.core.domain.repository.PairsInfoRepository
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
) : PairsInfoRepository {

    private val symbolsDao: BitfinexSymbolsDao by lazy {
        db.getBitfinexSymbolsDao()
    }

    private val syncInfoDao: BitfinexSyncInfoDao by lazy {
        db.getBitfinexSyncInfoDao()
    }

    override suspend fun downloadRemotePairsInfo() {
        val currencyPairs = api.getCurrencyPairs()
        symbolsDao.clearAll()
        symbolsDao.insert(mapper.mapSymbolsToDb(currencyPairs))
        syncInfoDao.insert(mapper.makeSyncInfoDbModel())
    }

    override suspend fun markAsDownloaded() {
        if (!settings.isAnyExchangeDownloaded) {
            settings.isAnyExchangeDownloaded = true
        }
    }
}
