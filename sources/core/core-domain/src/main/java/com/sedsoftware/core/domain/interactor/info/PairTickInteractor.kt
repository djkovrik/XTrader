package com.sedsoftware.core.domain.interactor.info

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.info.CurrencyPairTick
import com.sedsoftware.core.domain.interactor.Interactor
import com.sedsoftware.core.utils.common.Either
import com.sedsoftware.core.utils.common.Failure

interface PairTickInteractor : Interactor {
    fun getPairTick(pair: CurrencyPair): Either<Failure, CurrencyPairTick>
}
