package com.sedsoftware.core.interactor.info

import com.sedsoftware.core.common.Either
import com.sedsoftware.core.common.Failure
import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.info.CurrencyPairDepth
import com.sedsoftware.core.interactor.Interactor

interface PairAsksInteractor : Interactor {

    fun getPairAsks(pair: CurrencyPair): Either<Failure, List<CurrencyPairDepth>>
}
