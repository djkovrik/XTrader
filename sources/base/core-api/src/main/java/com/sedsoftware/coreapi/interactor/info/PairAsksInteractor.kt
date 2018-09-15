package com.sedsoftware.coreapi.interactor.info

import com.sedsoftware.coreapi.entity.CurrencyPair
import com.sedsoftware.coreapi.entity.info.CurrencyPairDepth
import com.sedsoftware.coreapi.interactor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure

interface PairAsksInteractor : Interactor {

    fun getPairAsks(pair: CurrencyPair): Either<Failure, List<CurrencyPairDepth>>
}
