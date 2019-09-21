package com.sedsoftware.exchange.binance

import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.tools.api.NetworkHandler
import com.sedsoftware.core.utils.extension.left
import com.sedsoftware.core.utils.extension.right
import com.sedsoftware.core.utils.type.Either
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.Failure.NetworkConnectionMissing
import com.sedsoftware.core.utils.type.Failure.PairsLoadingError
import com.sedsoftware.core.utils.type.Success
import com.sedsoftware.core.utils.type.Success.PairsLoadingCompleted
import com.sedsoftware.exchange.binance.repository.PairsInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinancePairLoader @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val repository: PairsInfoRepository
) : CurrencyPairLoader {

    override suspend fun fetchCurrencyPairs(): Either<Failure, Success> =
        withContext(Dispatchers.IO) {
            when (networkHandler.isConnected) {
                true -> {
                    try {
                        val remotePairs = repository.getRemotePairsInfo()
                        repository.storePairsInfo(remotePairs)
                        repository.storeSyncInfo(remotePairs)
                        repository.markAsDownloaded()
                        right(PairsLoadingCompleted)
                    } catch (exception: Exception) {
                        left(PairsLoadingError(exception))
                    }
                }
                false -> {
                    left(NetworkConnectionMissing)
                }
            }
        }
}
