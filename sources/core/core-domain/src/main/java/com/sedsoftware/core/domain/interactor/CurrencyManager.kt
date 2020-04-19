package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.repository.CurrencyRepository

interface CurrencyManager {

    val repository: CurrencyRepository

    suspend fun getCurrency(symbol: String): Currency =
        repository.getCurrency(symbol)
}
