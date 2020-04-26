package com.sedsoftware.core.domain.repository

interface CurrencyMapRepository {
    suspend fun downloadCurrencyMap()
}
