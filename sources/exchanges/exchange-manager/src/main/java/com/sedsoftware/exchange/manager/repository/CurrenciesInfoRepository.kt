package com.sedsoftware.exchange.manager.repository

import com.sedsoftware.exchange.manager.database.CurrencyDatabase
import com.sedsoftware.exchange.manager.database.dao.CurrencyDao
import com.sedsoftware.exchange.manager.database.dao.CurrencySyncInfoDao
import com.sedsoftware.exchange.manager.mapper.CurrencyInfoMapper
import com.sedsoftware.exchange.manager.network.CoinMarketCapApi
import com.sedsoftware.exchange.manager.network.model.CurrencyMap
import javax.inject.Inject

class CurrenciesInfoRepository @Inject constructor(
    private val api: CoinMarketCapApi,
    private val db: CurrencyDatabase,
    private val mapper: CurrencyInfoMapper
) {

    private val currencyDao: CurrencyDao by lazy {
        db.getCurrencyDao()
    }

    private val currencySyncInfoDao: CurrencySyncInfoDao by lazy {
        db.getCurrencySyncInfoDao()
    }

    suspend fun getCurrencyMap(): CurrencyMap =
        api.getCurrencyMap()

    suspend fun saveCurrencyMap(currencyMap: CurrencyMap) =
        currencyDao.insert(mapper.mapToDb(currencyMap))

    suspend fun saveSyncInfo(currencyMap: CurrencyMap) =
        currencySyncInfoDao.insert(mapper.mapSyncInfoToDb(currencyMap))

    suspend fun checkIfCurrenciesSaved(): Boolean {
        val count = currencySyncInfoDao.getSavedCurrencyCount()
        return count != null && count > 0
    }
}
