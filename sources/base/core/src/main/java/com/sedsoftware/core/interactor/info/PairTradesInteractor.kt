package com.sedsoftware.core.interactor.info

import com.sedsoftware.core.common.Either
import com.sedsoftware.core.common.Failure
import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.info.CurrencyPairTrade
import com.sedsoftware.core.interactor.Interactor

interface PairTradesInteractor : Interactor {

    fun getPairTrades(pair: CurrencyPair): Either<Failure, List<CurrencyPairTrade>>
}
