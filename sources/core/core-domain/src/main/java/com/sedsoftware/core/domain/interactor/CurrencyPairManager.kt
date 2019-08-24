package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.utils.type.Either
import com.sedsoftware.core.utils.type.Failure

interface CurrencyPairManager {
    suspend fun getBaseCurrencies(): Either<Failure, List<Currency>>
    suspend fun getMarketCurrencies(base: Currency): Either<Failure, List<Currency>>
}
