package com.sedsoftware.exchange.binance

import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.tools.api.NetworkHandler
import com.sedsoftware.core.utils.common.Either
import com.sedsoftware.core.utils.common.Either.Left
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.core.utils.common.Failure.NetworkConnectionMissing
import com.sedsoftware.core.utils.common.Success
import com.sedsoftware.exchange.binance.repository.PairsInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinancePairLoader @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val repository: PairsInfoRepository
) : CurrencyPairLoader {

    override suspend fun fetchCurrencyPairs(): Either<Failure, Success> =
        withContext(Dispatchers.IO) {
            when (networkHandler.isConnected) {
                true -> repository.getRemotePairsInfo()
                false -> Left(NetworkConnectionMissing)
            }
        }
}
