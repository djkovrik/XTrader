package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.entity.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyPairManager {
    suspend fun checkIfDataAvailable(): Flow<Boolean>
    suspend fun getBaseCurrencies(): Flow<List<Currency>>
    suspend fun getMarketCurrencies(base: Currency): Flow<List<Currency>>
}
