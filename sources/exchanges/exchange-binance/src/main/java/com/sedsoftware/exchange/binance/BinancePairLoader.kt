package com.sedsoftware.exchange.binance

import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.tools.api.NetworkHandler
import com.sedsoftware.core.utils.exception.NetworkConnectionMissing
import com.sedsoftware.exchange.binance.repository.BinancePairsInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class BinancePairLoader @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val repository: BinancePairsInfoRepository
) : CurrencyPairLoader {

    override suspend fun fetchCurrencyPairs(): Flow<Unit> = flow<Unit> {
        when (networkHandler.isConnected) {
            true -> {
                val remotePairs = repository.getRemotePairsInfo()
                repository.storePairsInfo(remotePairs)
                repository.storeSyncInfo(remotePairs)
                repository.markAsDownloaded()
            }
            false -> {
                throw NetworkConnectionMissing()
            }
        }
    }.flowOn(Dispatchers.IO)
}
