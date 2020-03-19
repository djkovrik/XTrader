package com.sedsoftware.exchange.bitfinex

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairsManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class BitfinexPairManager @Inject constructor(
    private val repository: BitfinexPairsManagerRepository
) : CurrencyPairManager {

    override suspend fun checkIfDataAvailable(): Flow<Boolean> = flow {
        emit(repository.isSynchronized())
    }.flowOn(Dispatchers.IO)

    override suspend fun getBaseCurrencies(): Flow<List<Currency>> = flow {
        val currencies =
            repository
                .getBaseCurrencies()
                .sortedBy { it.name }
                .distinctBy { it.name }

        emit(currencies)
    }.flowOn(Dispatchers.IO)

    override suspend fun getMarketCurrencies(base: Currency): Flow<List<Currency>> = flow {
        val currencies =
            repository
                .getMarketCurrencies(base)
                .sortedBy { it.name }

        emit(currencies)
    }.flowOn(Dispatchers.IO)
}
