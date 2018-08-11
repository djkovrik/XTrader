package com.sedsoftware.core.repository.info

import com.sedsoftware.core.common.Producer
import com.sedsoftware.core.entity.Currency
import com.sedsoftware.core.entity.info.CurrencyPair
import com.sedsoftware.core.repository.Repository

interface CurrencyPairsRepository : Repository {

    suspend fun fetchCurrencyPairs()

    suspend fun getCurrencyPairs(currency: Currency): Producer<List<CurrencyPair>>
}
