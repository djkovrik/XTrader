package com.sedsoftware.exchange.bitfinex

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.TickerManager
import com.sedsoftware.core.domain.repository.TickerRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import javax.inject.Inject

class BitfinexTickerManager @Inject constructor(
    @ForExchange(BITFINEX) override val repository: TickerRepository,
    override val networkHandler: NetworkHandler
) : TickerManager
