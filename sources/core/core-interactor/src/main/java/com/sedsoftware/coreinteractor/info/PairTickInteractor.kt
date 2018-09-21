package com.sedsoftware.coreinteractor.info

import com.sedsoftware.coreentity.CurrencyPair
import com.sedsoftware.coreentity.info.CurrencyPairTick
import com.sedsoftware.coreinteractor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure

interface PairTickInteractor : Interactor {

    fun getPairTick(pair: CurrencyPair): Either<Failure, CurrencyPairTick>
}
