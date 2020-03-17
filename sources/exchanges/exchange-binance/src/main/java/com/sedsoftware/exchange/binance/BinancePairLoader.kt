package com.sedsoftware.exchange.binance

import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.tools.api.NetworkHandler
import com.sedsoftware.core.utils.exception.NetworkConnectionMissing
import com.sedsoftware.exchange.binance.repository.BinancePairsInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinancePairLoader @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val repository: BinancePairsInfoRepository
) : CurrencyPairLoader {

    override suspend fun fetchCurrencyPairs(): Flow<Unit> = flow {
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
    }
}
