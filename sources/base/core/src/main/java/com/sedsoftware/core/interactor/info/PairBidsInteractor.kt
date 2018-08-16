package com.sedsoftware.core.interactor.info

import com.sedsoftware.core.common.Either
import com.sedsoftware.core.common.Failure
import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.info.CurrencyPairDepth
import com.sedsoftware.core.interactor.Interactor

interface PairBidsInteractor : Interactor {

    fun getPairBids(pair: CurrencyPair): Either<Failure, List<CurrencyPairDepth>>
}
