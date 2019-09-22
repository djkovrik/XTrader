package com.sedsoftware.exchange.coinmarketcap.repository

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.exchange.coinmarketcap.database.CurrencyDatabase
import com.sedsoftware.exchange.coinmarketcap.database.dao.CurrencyDao
import com.sedsoftware.exchange.coinmarketcap.mapper.CurrencyInfoMapper
import javax.inject.Inject

class CurrencyNameRepository @Inject constructor(
    private val db: CurrencyDatabase,
    private val mapper: CurrencyInfoMapper
) {

    private val currencyDao: CurrencyDao by lazy {
        db.getCurrencyDao()
    }

    suspend fun getCurrency(symbol: String): Currency {
        val dbCurrency = currencyDao.getBySymbol(symbol)
        return mapper.mapDbToCurrency(dbCurrency)
    }
}
