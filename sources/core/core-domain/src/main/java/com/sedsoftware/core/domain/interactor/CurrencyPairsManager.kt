package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.repository.PairsManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface CurrencyPairsManager {

    val repository: PairsManagerRepository

    suspend fun checkIfDataAvailable(): Flow<Boolean> = flow {
        emit(repository.isSynchronized())
    }.flowOn(Dispatchers.IO)

    suspend fun getBaseCurrencies(): Flow<List<Currency>> = flow {
        val currencies =
            repository
                .getBaseCurrencies()
                .sortedBy { it.name }
                .distinctBy { it.name }

        emit(currencies)
    }.flowOn(Dispatchers.IO)

    suspend fun getMarketCurrencies(base: Currency): Flow<List<Currency>> = flow {
        val currencies =
            repository
                .getMarketCurrencies(base)
                .sortedBy { it.name }

        emit(currencies)
    }.flowOn(Dispatchers.IO)
}
