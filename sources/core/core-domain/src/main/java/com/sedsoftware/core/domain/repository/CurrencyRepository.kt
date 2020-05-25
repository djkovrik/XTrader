package com.sedsoftware.core.domain.repository

import com.sedsoftware.core.domain.entity.Currency

interface CurrencyRepository {
    suspend fun getCurrency(name: String): Currency
}
