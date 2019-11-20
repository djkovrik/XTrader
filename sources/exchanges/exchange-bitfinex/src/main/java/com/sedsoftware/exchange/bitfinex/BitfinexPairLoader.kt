package com.sedsoftware.exchange.bitfinex

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
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairsInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BitfinexPairLoader @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val repository: BitfinexPairsInfoRepository
) : CurrencyPairLoader {

    override suspend fun fetchCurrencyPairs(): Either<Failure, Success> =
        withContext(Dispatchers.IO) {
            when (networkHandler.isConnected) {
                true -> {
                    try {
                        val remotePairs = repository.getRemotePairsInfo()
                        if (remotePairs.isNotEmpty()) {
                            repository.storePairsInfo(remotePairs)
                            repository.storeSyncInfo()
                            repository.markAsDownloaded()
                        }
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
