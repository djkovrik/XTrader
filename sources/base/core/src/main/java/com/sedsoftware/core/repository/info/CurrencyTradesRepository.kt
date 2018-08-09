package com.sedsoftware.core.repository.info

import com.sedsoftware.core.entity.Currency
import com.sedsoftware.core.entity.info.CurrencyTrade
import com.sedsoftware.core.repository.Repository
import kotlinx.coroutines.experimental.channels.ReceiveChannel

interface CurrencyTradesRepository : Repository {

    suspend fun fetchCurrencyTrades()

    suspend fun getCurrencyTrades(currency: Currency): ReceiveChannel<List<CurrencyTrade>>
}
