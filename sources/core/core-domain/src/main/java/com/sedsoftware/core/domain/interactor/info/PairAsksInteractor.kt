package com.sedsoftware.core.domain.interactor.info

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.info.CurrencyPairDepth
import com.sedsoftware.core.domain.interactor.Interactor
import com.sedsoftware.core.utils.common.Either
import com.sedsoftware.core.utils.common.Failure

interface PairAsksInteractor : Interactor {
    fun getPairAsks(pair: CurrencyPair): Either<Failure, List<CurrencyPairDepth>>
}
