package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import com.sedsoftware.core.domain.exception.NetworkConnectionMissing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface CurrencyPairsLoader {

    val networkHandler: NetworkHandler
    val repository: PairsInfoRepository

    suspend fun fetchCurrencyPairs(): Flow<Unit> = flow<Unit> {
        when (networkHandler.isConnected) {
            true -> {
                repository.downloadRemotePairsInfo()
                repository.markAsDownloaded()
            }
            false -> {
                throw NetworkConnectionMissing()
            }
        }
    }.flowOn(Dispatchers.IO)
}
