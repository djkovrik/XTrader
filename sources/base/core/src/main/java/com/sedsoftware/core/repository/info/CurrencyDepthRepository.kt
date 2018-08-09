package com.sedsoftware.core.repository.info

import com.sedsoftware.core.entity.Currency
import com.sedsoftware.core.entity.info.CurrencyDepth
import com.sedsoftware.core.repository.Repository
import kotlinx.coroutines.experimental.channels.ReceiveChannel

interface CurrencyDepthRepository : Repository {

    suspend fun fetchCurrencyDepths()

    suspend fun getCurrencyBids(currency: Currency): ReceiveChannel<List<CurrencyDepth>>

    suspend fun getCurrencyAsks(currency: Currency): ReceiveChannel<List<CurrencyDepth>>
}
