package com.sedsoftware.exchange.bitfinex

import com.sedsoftware.core.di.annotations.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.PairTicksManager
import com.sedsoftware.core.domain.repository.PairsTickRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import javax.inject.Inject

class BitfinexPairTicksManager @Inject constructor(
    @ForExchange(BITFINEX) override val repository: PairsTickRepository,
    override val networkHandler: NetworkHandler
) : PairTicksManager
