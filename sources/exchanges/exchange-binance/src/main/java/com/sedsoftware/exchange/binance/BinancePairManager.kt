package com.sedsoftware.exchange.binance

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.utils.extension.left
import com.sedsoftware.core.utils.extension.right
import com.sedsoftware.core.utils.type.Either
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.Failure.PairsManagerError
import com.sedsoftware.exchange.binance.repository.BinancePairsManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinancePairManager @Inject constructor(
    private val repository: BinancePairsManagerRepository
) : CurrencyPairManager {

    override suspend fun checkIfDataAvailable(): Either<Failure, Boolean> =
        withContext(Dispatchers.IO) {
            try {
                val synced = repository.isSynchronized()

                right(synced)
            } catch (exception: Exception) {
                left(PairsManagerError(exception))
            }
        }

    override suspend fun getBaseCurrencies(): Either<Failure, List<Currency>> =
        withContext(Dispatchers.IO) {
            try {
                val currencies =
                    repository
                        .getBaseCurrencies()
                        .sortedBy { it.name }
                        .distinctBy { it.name }

                right(currencies)
            } catch (exception: Exception) {
                left(PairsManagerError(exception))
            }
        }

    override suspend fun getMarketCurrencies(base: Currency): Either<Failure, List<Currency>> =
        withContext(Dispatchers.IO) {
            try {
                val currencies =
                    repository
                        .getMarketCurrencies(base)
                        .sortedBy { it.name }

                right(currencies)
            } catch (exception: Exception) {
                left(PairsManagerError(exception))
            }
        }
}
