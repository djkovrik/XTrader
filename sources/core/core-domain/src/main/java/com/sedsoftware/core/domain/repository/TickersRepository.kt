package com.sedsoftware.core.domain.repository

import com.sedsoftware.core.domain.entity.Currency

interface TickersRepository {
    suspend fun isSynchronized(): Boolean
    suspend fun getBaseCurrencies(): List<Currency>
    suspend fun getMarketCurrencies(base: Currency): List<Currency>
}
