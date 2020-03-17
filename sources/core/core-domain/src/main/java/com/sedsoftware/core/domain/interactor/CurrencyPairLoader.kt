package com.sedsoftware.core.domain.interactor

import kotlinx.coroutines.flow.Flow

interface CurrencyPairLoader {
    suspend fun fetchCurrencyPairs(): Flow<Unit>
}
