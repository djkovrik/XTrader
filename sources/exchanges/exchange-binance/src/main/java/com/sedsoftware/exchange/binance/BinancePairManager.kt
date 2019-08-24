package com.sedsoftware.exchange.binance

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.utils.extension.left
import com.sedsoftware.core.utils.extension.right
import com.sedsoftware.core.utils.type.Either
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.Failure.PairsManagerFailure
import com.sedsoftware.exchange.binance.repository.PairsManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinancePairManager @Inject constructor(
    private val repository: PairsManagerRepository
) : CurrencyPairManager {

    override suspend fun getBaseCurrencies(): Either<Failure, List<Currency>> =
        withContext(Dispatchers.IO) {
            try {
                val currencies =
                    repository
                        .getBaseCurrencies()
                        .sortedBy { it.name }
                        .distinct()
                right(currencies)
            } catch (exception: Exception) {
                left(PairsManagerFailure(exception))
            }
        }

    override suspend fun getMarketCurrencies(base: Currency): Either<Failure, List<Currency>> =
        withContext(Dispatchers.IO) {
            try {
                val currencies =
                    repository
                        .getMarketCurrencies(base)
                        .sortedBy { it.name }
                        .distinct()
                right(currencies)
            } catch (exception: Exception) {
                left(PairsManagerFailure(exception))
            }
        }
}
