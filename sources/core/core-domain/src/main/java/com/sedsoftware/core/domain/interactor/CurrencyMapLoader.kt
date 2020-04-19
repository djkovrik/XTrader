package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.exception.CurrencyMapLoadingError
import com.sedsoftware.core.domain.exception.NetworkConnectionMissing
import com.sedsoftware.core.domain.repository.CurrencyMapRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CurrencyMapLoader {

    val repository: CurrencyMapRepository
    val networkHandler: NetworkHandler

    suspend fun loadCurrencyMap() = withContext(Dispatchers.IO) {
        if (repository.isLoadingNeeded()) {
            when (networkHandler.isConnected) {
                true -> {
                    try {
                        repository.downloadCurrencyMap()
                    } catch (exception: Throwable) {
                        throw CurrencyMapLoadingError(exception)
                    }
                }
                false -> {
                    throw NetworkConnectionMissing()
                }
            }
        }
    }
}
