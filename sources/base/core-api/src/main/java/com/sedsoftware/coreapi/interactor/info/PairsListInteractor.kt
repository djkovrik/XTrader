package com.sedsoftware.coreapi.interactor.info

import com.sedsoftware.coreapi.entity.Currency
import com.sedsoftware.coreapi.interactor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure

interface PairsListInteractor : Interactor {

    fun getBaseCurrencies(): Either<Failure, List<Currency>>

    fun getMarketCurrencies(baseCurrency: Currency): Either<Failure, List<Currency>>
}
