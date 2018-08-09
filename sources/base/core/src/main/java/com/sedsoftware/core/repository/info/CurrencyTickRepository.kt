package com.sedsoftware.core.repository.info

import com.sedsoftware.core.entity.Currency
import com.sedsoftware.core.entity.info.CurrencyTick
import com.sedsoftware.core.repository.Repository
import kotlinx.coroutines.experimental.channels.ReceiveChannel

interface CurrencyTickRepository : Repository {

    suspend fun fetchCurrencyTick()

    suspend fun getCurrencyTick(currency: Currency): ReceiveChannel<CurrencyTick>
}
