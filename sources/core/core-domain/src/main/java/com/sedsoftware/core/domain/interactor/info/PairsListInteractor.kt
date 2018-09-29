package com.sedsoftware.core.domain.interactor.info

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.interactor.Interactor
import com.sedsoftware.core.utils.common.Either
import com.sedsoftware.core.utils.common.Failure

interface PairsListInteractor : Interactor {
    fun getBaseCurrencies(): Either<Failure, List<Currency>>
    fun getMarketCurrencies(baseCurrency: Currency): Either<Failure, List<Currency>>
}
