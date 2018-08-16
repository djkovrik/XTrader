package com.sedsoftware.core.interactor.info

import com.sedsoftware.core.common.Either
import com.sedsoftware.core.common.Failure
import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.info.CurrencyPairTick
import com.sedsoftware.core.interactor.Interactor

interface PairTickInteractor : Interactor {

    fun getPairTick(pair: CurrencyPair): Either<Failure, CurrencyPairTick>
}
