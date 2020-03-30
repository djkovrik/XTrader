package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.repository.CurrencyMapRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import com.sedsoftware.core.domain.exception.CurrencyMapLoadingError
import com.sedsoftware.core.domain.exception.NetworkConnectionMissing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface CurrencyMapLoader {

    val repository: CurrencyMapRepository
    val networkHandler: NetworkHandler

    suspend fun isLoadingNeeded(): Flow<Boolean> = flow {
        emit(repository.isLoadingNeeded())
    }.flowOn(Dispatchers.IO)

    suspend fun loadCurrencyMap(): Flow<Unit> = flow<Unit> {
        when (networkHandler.isConnected) {
            true -> {
                try {
                    repository.downloadCurrencyMap()
                } catch (exception: Exception) {
                    throw CurrencyMapLoadingError(exception)
                }
            }
            false -> {
                throw NetworkConnectionMissing()
            }
        }
    }.flowOn(Dispatchers.IO)
}
