package com.sedsoftware.core.repository.info

import com.sedsoftware.core.common.Producer
import com.sedsoftware.core.entity.Currency
import com.sedsoftware.core.entity.info.CurrencyTick
import com.sedsoftware.core.repository.Repository

interface CurrencyTickRepository : Repository {

    suspend fun fetchCurrencyTick()

    suspend fun getCurrencyTick(currency: Currency): Producer<CurrencyTick>
}
