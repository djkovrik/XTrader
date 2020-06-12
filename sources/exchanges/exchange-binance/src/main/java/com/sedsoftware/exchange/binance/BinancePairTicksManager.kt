package com.sedsoftware.exchange.binance

import com.sedsoftware.core.di.annotations.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.PairTicksManager
import com.sedsoftware.core.domain.repository.PairsTickRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import javax.inject.Inject

class BinancePairTicksManager @Inject constructor(
    @ForExchange(BINANCE) override val repository: PairsTickRepository,
    override val networkHandler: NetworkHandler
) : PairTicksManager
