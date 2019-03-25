package com.sedsoftware.exchange.binance

import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.tools.api.NetworkHandler
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.core.utils.common.Either
import com.sedsoftware.core.utils.common.Either.Right
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.core.utils.common.Success
import com.sedsoftware.exchange.binance.repository.PairsInfoRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinancePairLoader @Inject constructor(
    private val repository: PairsInfoRepository,
    private val networkHandler: NetworkHandler,
    private val settings: Settings
) : CurrencyPairLoader {

    override suspend fun fetchCurrencyPairs(): Either<Failure, Success> {
        delay(2500L)
        return Right(Success.PairsLoadingCompleted)
    }
}
