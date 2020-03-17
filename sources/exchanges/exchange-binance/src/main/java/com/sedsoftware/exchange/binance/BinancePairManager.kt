package com.sedsoftware.exchange.binance

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.exchange.binance.repository.BinancePairsManagerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinancePairManager @Inject constructor(
    private val repository: BinancePairsManagerRepository
) : CurrencyPairManager {

    override suspend fun checkIfDataAvailable(): Flow<Boolean> = flow {
        emit(repository.isSynchronized())
    }

    override suspend fun getBaseCurrencies(): Flow<List<Currency>> = flow {
        val currencies =
            repository
                .getBaseCurrencies()
                .sortedBy { it.name }
                .distinctBy { it.name }

        emit(currencies)
    }

    override suspend fun getMarketCurrencies(base: Currency): Flow<List<Currency>> = flow {
        val currencies =
            repository
                .getMarketCurrencies(base)
                .sortedBy { it.name }

        emit(currencies)
    }
}
