package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.exception.TickerPairsLoadingError
import com.sedsoftware.core.domain.repository.TickersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface TickersManager {

    val repository: TickersRepository

    suspend fun checkIfDataAvailable(): Boolean = withContext(Dispatchers.IO) {
        repository.isSynchronized()
    }

    suspend fun getBaseCurrencies(): List<Currency> = withContext(Dispatchers.IO) {
        try {
            repository
                .getBaseCurrencies()
                .sortedBy { it.name }
                .distinctBy { it.name }
        } catch (throwable: Throwable) {
            throw TickerPairsLoadingError(throwable)
        }
    }

    suspend fun getMarketCurrencies(base: Currency): List<Currency> = withContext(Dispatchers.IO) {
        try {
            repository
                .getMarketCurrencies(base)
                .sortedBy { it.name }
        } catch (throwable: Throwable) {
            throw TickerPairsLoadingError(throwable)
        }
    }
}
