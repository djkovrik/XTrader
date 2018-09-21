package com.sedsoftware.coreinteractor.info

import com.sedsoftware.coreentity.Currency
import com.sedsoftware.coreinteractor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure

interface PairsListInteractor : Interactor {

    fun getBaseCurrencies(): Either<Failure, List<Currency>>

    fun getMarketCurrencies(baseCurrency: Currency): Either<Failure, List<Currency>>
}
