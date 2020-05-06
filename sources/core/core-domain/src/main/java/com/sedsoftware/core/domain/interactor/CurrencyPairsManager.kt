package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.repository.PairsManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CurrencyPairsManager {

    val repository: PairsManagerRepository

    suspend fun checkIfDataAvailable(): Boolean = withContext(Dispatchers.IO) {
        repository.isSynchronized()
    }

    suspend fun getBaseCurrencies(): List<Currency> = withContext(Dispatchers.IO) {
        repository
            .getBaseCurrencies()
            .sortedBy { it.name }
            .distinctBy { it.name }
    }

    suspend fun getMarketCurrencies(base: Currency): List<Currency> = withContext(Dispatchers.IO) {
        repository
            .getMarketCurrencies(base)
            .sortedBy { it.name }
    }
}
