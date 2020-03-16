package com.sedsoftware.core.domain.interactor

import kotlinx.coroutines.flow.Flow

interface CurrencyMapLoader {
    suspend fun isLoadingNeeded(): Flow<Boolean>
    suspend fun loadCurrencyMap(): Flow<Unit>
}
