package com.sedsoftware.exchange.bitfinex

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.TickersManager
import com.sedsoftware.core.domain.repository.TickersRepository
import javax.inject.Inject

class BitfinexTickersManager @Inject constructor(
    @ForExchange(BITFINEX) override val repository: TickersRepository
) : TickersManager
