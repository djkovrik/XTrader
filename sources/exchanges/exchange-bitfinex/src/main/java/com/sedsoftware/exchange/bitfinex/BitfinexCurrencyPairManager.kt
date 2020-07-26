package com.sedsoftware.exchange.bitfinex

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.domain.repository.PairManagerRepository
import javax.inject.Inject

class BitfinexCurrencyPairManager @Inject constructor(
    @ForExchange(BITFINEX) override val repository: PairManagerRepository
) : CurrencyPairManager
