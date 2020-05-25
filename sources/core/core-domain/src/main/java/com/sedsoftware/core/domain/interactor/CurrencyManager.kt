package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.repository.CurrencyRepository

interface CurrencyManager {

    val repository: CurrencyRepository

    suspend fun getCurrency(name: String): Currency =
        repository.getCurrency(name)
}
