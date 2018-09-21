package com.sedsoftware.corerepository.info

import com.sedsoftware.coreentity.Currency
import com.sedsoftware.coreentity.CurrencyPair
import com.sedsoftware.corerepository.Repository
import com.sedsoftware.coreutils.common.Producer

interface CurrencyPairsRepository : Repository {

    suspend fun fetchAllCurrencyPairs()

    suspend fun getAllBaseCurrencies(): Producer<List<Currency>>

    suspend fun getCurrenciesPairsForBase(baseCurrency: Currency): Producer<List<CurrencyPair>>
}
