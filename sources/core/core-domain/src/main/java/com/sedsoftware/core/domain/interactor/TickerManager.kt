package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.CurrencyPairTick
import com.sedsoftware.core.domain.exception.CurrencyTickLoadingError
import com.sedsoftware.core.domain.exception.NetworkConnectionMissing
import com.sedsoftware.core.domain.repository.TickerRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface TickerManager {

    val repository: TickerRepository
    val networkHandler: NetworkHandler

    suspend fun hasTicks(): Boolean = withContext(Dispatchers.IO) {
        repository.hasTickers()
    }

    suspend fun addPairToWatchList(pair: CurrencyPair) = withContext(Dispatchers.IO) {
        repository.addPairToWatchList(pair)
        fetchPairPrice(pair)
    }

    suspend fun removePairFromWatchList(pair: CurrencyPair) = withContext(Dispatchers.IO) {
        repository.removePairFromWatchList(pair)
    }

    suspend fun watchForTicks(): Flow<List<CurrencyPairTick>> = withContext(Dispatchers.IO) {
        repository.watchForTickers()
    }

    suspend fun refreshTicks() = withContext(Dispatchers.IO) {
        val ticks = repository.getCurrentWatchList()
        ticks.forEach { tick ->
            fetchPairPrice(tick.pair)
        }
    }

    private suspend fun fetchPairPrice(pair: CurrencyPair) {
        when (networkHandler.isConnected) {
            true -> {
                try {
                    val price = repository.fetchPrice(pair)
                    repository.refreshPrice(pair, price)
                } catch (exception: Throwable) {
                    throw CurrencyTickLoadingError(pair.symbol, exception)
                }
            }
            false -> {
                throw NetworkConnectionMissing()
            }
        }
    }
}
