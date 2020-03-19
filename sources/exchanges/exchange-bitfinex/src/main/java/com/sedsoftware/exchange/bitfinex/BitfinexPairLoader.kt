package com.sedsoftware.exchange.bitfinex

import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.tools.api.NetworkHandler
import com.sedsoftware.core.utils.exception.NetworkConnectionMissing
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairsInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class BitfinexPairLoader @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val repository: BitfinexPairsInfoRepository
) : CurrencyPairLoader {

    override suspend fun fetchCurrencyPairs(): Flow<Unit> = flow<Unit> {
        when (networkHandler.isConnected) {
            true -> {
                val remotePairs = repository.getRemotePairsInfo()
                if (remotePairs.isNotEmpty()) {
                    repository.storePairsInfo(remotePairs)
                    repository.storeSyncInfo()
                    repository.markAsDownloaded()
                }
            }
            false -> {
                throw NetworkConnectionMissing()
            }
        }
    }.flowOn(Dispatchers.IO)
}
