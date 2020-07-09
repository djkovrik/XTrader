package com.sedsoftware.exchange.binance

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.TickersManager
import com.sedsoftware.core.domain.repository.TickersRepository
import javax.inject.Inject

class BinanceTickersManager @Inject constructor(
    @ForExchange(BINANCE) override val repository: TickersRepository
) : TickersManager
