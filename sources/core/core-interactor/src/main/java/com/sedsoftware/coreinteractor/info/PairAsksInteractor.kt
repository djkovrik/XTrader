package com.sedsoftware.coreinteractor.info

import com.sedsoftware.coreentity.CurrencyPair
import com.sedsoftware.coreentity.info.CurrencyPairDepth
import com.sedsoftware.coreinteractor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure

interface PairAsksInteractor : Interactor {

    fun getPairAsks(pair: CurrencyPair): Either<Failure, List<CurrencyPairDepth>>
}
