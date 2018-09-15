package com.sedsoftware.coreapi.repository.info

import com.sedsoftware.coreapi.entity.Currency
import com.sedsoftware.coreapi.entity.CurrencyPair
import com.sedsoftware.coreapi.repository.Repository
import com.sedsoftware.coreutils.common.Producer

interface CurrencyPairsRepository : Repository {

    suspend fun fetchAllCurrencyPairs()

    suspend fun getAllBaseCurrencies(): Producer<List<Currency>>

    suspend fun getCurrenciesPairsForBase(baseCurrency: Currency): Producer<List<CurrencyPair>>
}
