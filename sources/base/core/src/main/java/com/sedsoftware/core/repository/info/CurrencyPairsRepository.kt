package com.sedsoftware.core.repository.info

import com.sedsoftware.core.entity.Currency
import com.sedsoftware.core.entity.info.CurrencyPair
import com.sedsoftware.core.repository.Repository
import kotlinx.coroutines.experimental.channels.ReceiveChannel

interface CurrencyPairsRepository : Repository {

    suspend fun fetchCurrencyPairs()

    suspend fun getCurrencyPairs(currency: Currency): ReceiveChannel<List<CurrencyPair>>
}
