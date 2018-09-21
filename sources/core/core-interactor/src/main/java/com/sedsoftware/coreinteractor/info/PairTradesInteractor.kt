package com.sedsoftware.coreinteractor.info

import com.sedsoftware.coreentity.CurrencyPair
import com.sedsoftware.coreentity.info.CurrencyPairTrade
import com.sedsoftware.coreinteractor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure

interface PairTradesInteractor : Interactor {

    fun getPairTrades(pair: CurrencyPair): Either<Failure, List<CurrencyPairTrade>>
}
