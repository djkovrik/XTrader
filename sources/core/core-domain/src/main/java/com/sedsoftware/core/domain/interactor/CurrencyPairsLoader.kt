package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.exception.CurrencyPairsLoadingError
import com.sedsoftware.core.domain.exception.NetworkConnectionMissing
import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CurrencyPairsLoader {

    val networkHandler: NetworkHandler
    val repository: PairsInfoRepository

    suspend fun fetchCurrencyPairs() = withContext(Dispatchers.IO) {
        when (networkHandler.isConnected) {
            true -> {
                try {
                    repository.downloadRemotePairsInfo()
                    repository.markAsDownloaded()
                } catch (exception: Throwable) {
                    throw  CurrencyPairsLoadingError(exception)
                }
            }
            false -> {
                throw NetworkConnectionMissing()
            }
        }
    }
}
