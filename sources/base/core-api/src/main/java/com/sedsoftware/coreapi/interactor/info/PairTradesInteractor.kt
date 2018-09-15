package com.sedsoftware.coreapi.interactor.info

import com.sedsoftware.coreapi.entity.CurrencyPair
import com.sedsoftware.coreapi.entity.info.CurrencyPairTrade
import com.sedsoftware.coreapi.interactor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure

interface PairTradesInteractor : Interactor {

    fun getPairTrades(pair: CurrencyPair): Either<Failure, List<CurrencyPairTrade>>
}
