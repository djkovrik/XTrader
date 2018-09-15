package com.sedsoftware.coreapi.interactor.info

import com.sedsoftware.coreapi.entity.CurrencyPair
import com.sedsoftware.coreapi.entity.info.CurrencyPairTick
import com.sedsoftware.coreapi.interactor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure

interface PairTickInteractor : Interactor {

    fun getPairTick(pair: CurrencyPair): Either<Failure, CurrencyPairTick>
}
