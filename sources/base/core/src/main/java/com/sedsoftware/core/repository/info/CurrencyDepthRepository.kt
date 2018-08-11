package com.sedsoftware.core.repository.info

import com.sedsoftware.core.common.Producer
import com.sedsoftware.core.entity.Currency
import com.sedsoftware.core.entity.info.CurrencyDepth
import com.sedsoftware.core.repository.Repository

interface CurrencyDepthRepository : Repository {

    suspend fun fetchCurrencyDepths()

    suspend fun getCurrencyBids(currency: Currency): Producer<List<CurrencyDepth>>

    suspend fun getCurrencyAsks(currency: Currency): Producer<List<CurrencyDepth>>
}
