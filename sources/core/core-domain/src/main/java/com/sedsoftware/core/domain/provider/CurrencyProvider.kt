package com.sedsoftware.core.domain.provider

import com.sedsoftware.core.domain.entity.Currency

interface CurrencyProvider {
    suspend fun getCurrency(symbol: String): Currency
}
