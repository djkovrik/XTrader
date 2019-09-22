package com.sedsoftware.exchange.coinmarketcap

import com.sedsoftware.core.domain.interactor.CurrenciesInfoLoader
import com.sedsoftware.core.tools.api.NetworkHandler
import com.sedsoftware.core.utils.extension.left
import com.sedsoftware.core.utils.extension.right
import com.sedsoftware.core.utils.type.Either
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.Failure.CurrencyMapLoadingError
import com.sedsoftware.core.utils.type.Failure.NetworkConnectionMissing
import com.sedsoftware.core.utils.type.Success
import com.sedsoftware.core.utils.type.Success.CurrencyMapLoadingCompleted
import com.sedsoftware.exchange.coinmarketcap.repository.CurrenciesInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinMarketCapInfoLoader @Inject constructor(
    private val repository: CurrenciesInfoRepository,
    private val networkHandler: NetworkHandler
) : CurrenciesInfoLoader {

    override suspend fun loadInfoIfNeeded(): Either<Failure, Success> =
        withContext(Dispatchers.IO) {
            when (networkHandler.isConnected) {
                true -> {
                    try {
                        if (repository.isLoadingNeeded()) {
                            val currencyMap = repository.getCurrencyMap()
                            repository.saveCurrencyMap(currencyMap)
                            repository.saveSyncInfo(currencyMap)

                        }
                        right(CurrencyMapLoadingCompleted)
                    } catch (exception: Exception) {
                        left(CurrencyMapLoadingError(exception))
                    }
                }
                false -> {
                    left(NetworkConnectionMissing)
                }
            }
        }
}
