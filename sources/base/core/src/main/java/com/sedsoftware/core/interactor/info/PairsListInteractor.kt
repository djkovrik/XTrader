package com.sedsoftware.core.interactor.info

import com.sedsoftware.core.common.Either
import com.sedsoftware.core.common.Failure
import com.sedsoftware.core.entity.Currency
import com.sedsoftware.core.interactor.Interactor

interface PairsListInteractor : Interactor {

    fun getBaseCurrencies(): Either<Failure, List<Currency>>

    fun getMarketCurrencies(baseCurrency: Currency): Either<Failure, List<Currency>>
}
