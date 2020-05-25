package com.sedsoftware.exchange.coinmarketcap.repository

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.repository.CurrencyRepository
import com.sedsoftware.exchange.coinmarketcap.database.CurrencyDatabase
import com.sedsoftware.exchange.coinmarketcap.database.dao.CurrencyDao
import com.sedsoftware.exchange.coinmarketcap.mapper.CurrencyInfoMapper
import javax.inject.Inject

class CoinMarketCapCurrencyRepository @Inject constructor(
    private val db: CurrencyDatabase,
    private val mapper: CurrencyInfoMapper
) : CurrencyRepository {

    private val currencyDao: CurrencyDao by lazy {
        db.getCurrencyDao()
    }

    override suspend fun getCurrency(name: String): Currency {
        val dbCurrency = currencyDao.getByName(name)
        return mapper.mapDbToCurrency(dbCurrency, name)
    }
}
