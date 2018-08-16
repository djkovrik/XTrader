package com.sedsoftware.core.repository.info

import com.sedsoftware.core.common.Producer
import com.sedsoftware.core.entity.Currency
import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.repository.Repository

interface CurrencyPairsRepository : Repository {

    suspend fun fetchAllCurrencyPairs()

    suspend fun getAllBaseCurrencies(): Producer<List<Currency>>

    suspend fun getMarketCurrenciesForBase(baseCurrency: Currency): Producer<List<Currency>>
}
