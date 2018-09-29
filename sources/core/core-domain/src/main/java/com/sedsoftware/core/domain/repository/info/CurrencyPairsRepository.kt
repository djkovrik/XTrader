package com.sedsoftware.core.domain.repository.info

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.repository.Repository
import com.sedsoftware.core.utils.common.Producer

interface CurrencyPairsRepository : Repository {
    suspend fun fetchAllCurrencyPairs()
    suspend fun getAllBaseCurrencies(): Producer<List<Currency>>
    suspend fun getCurrenciesPairsForBase(baseCurrency: Currency): Producer<List<CurrencyPair>>
}
