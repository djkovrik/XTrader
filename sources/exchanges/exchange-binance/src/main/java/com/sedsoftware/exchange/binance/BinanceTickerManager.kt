package com.sedsoftware.exchange.binance

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.TickerManager
import com.sedsoftware.core.domain.repository.TickerRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import javax.inject.Inject

class BinanceTickerManager @Inject constructor(
    @ForExchange(BINANCE) override val repository: TickerRepository,
    override val networkHandler: NetworkHandler
) : TickerManager
