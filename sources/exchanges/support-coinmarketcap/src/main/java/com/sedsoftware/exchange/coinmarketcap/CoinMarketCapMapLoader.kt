package com.sedsoftware.exchange.coinmarketcap

import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.core.tools.api.NetworkHandler
import com.sedsoftware.core.utils.exception.CurrencyMapLoadingError
import com.sedsoftware.core.utils.exception.NetworkConnectionMissing
import com.sedsoftware.exchange.coinmarketcap.repository.CurrenciesInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class CoinMarketCapMapLoader @Inject constructor(
    private val repository: CurrenciesInfoRepository,
    private val networkHandler: NetworkHandler
) : CurrencyMapLoader {

    override suspend fun isLoadingNeeded(): Flow<Boolean> = flow {
        emit(repository.isLoadingNeeded())
    }.flowOn(Dispatchers.IO)

    override suspend fun loadCurrencyMap(): Flow<Unit> = flow {
        when (networkHandler.isConnected) {
            true -> {
                try {
                    val currencyMap = repository.getCurrencyMap()
                    repository.saveCurrencyMap(currencyMap)
                    repository.saveSyncInfo(currencyMap)
                    emit(Unit)
                } catch (exception: Exception) {
                    throw CurrencyMapLoadingError(exception)
                }
            }
            false -> {
                throw NetworkConnectionMissing()
            }
        }
    }.flowOn(Dispatchers.IO)
}
