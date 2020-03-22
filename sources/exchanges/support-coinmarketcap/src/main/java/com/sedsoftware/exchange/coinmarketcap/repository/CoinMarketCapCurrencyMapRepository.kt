package com.sedsoftware.exchange.coinmarketcap.repository

import com.sedsoftware.core.domain.repository.CurrencyMapRepository
import com.sedsoftware.exchange.coinmarketcap.database.CurrencyDatabase
import com.sedsoftware.exchange.coinmarketcap.database.dao.CurrencyDao
import com.sedsoftware.exchange.coinmarketcap.database.dao.CurrencySyncInfoDao
import com.sedsoftware.exchange.coinmarketcap.mapper.CurrencyInfoMapper
import com.sedsoftware.exchange.coinmarketcap.network.CoinMarketCapApi
import javax.inject.Inject

class CoinMarketCapCurrencyMapRepository @Inject constructor(
    private val api: CoinMarketCapApi,
    private val db: CurrencyDatabase,
    private val mapper: CurrencyInfoMapper
) : CurrencyMapRepository {

    private val currencyDao: CurrencyDao by lazy {
        db.getCurrencyDao()
    }

    private val currencySyncInfoDao: CurrencySyncInfoDao by lazy {
        db.getCurrencySyncInfoDao()
    }

    override suspend fun downloadCurrencyMap() {
        val currencyMap = api.getCurrencyMap()
        currencyDao.insert(mapper.mapToDb(currencyMap))
        currencySyncInfoDao.insert(mapper.mapSyncInfoToDb(currencyMap))
    }

    override suspend fun isLoadingNeeded(): Boolean {
        val count = currencySyncInfoDao.getSavedCurrencyCount()
        return count == null || count == 0
    }
}
