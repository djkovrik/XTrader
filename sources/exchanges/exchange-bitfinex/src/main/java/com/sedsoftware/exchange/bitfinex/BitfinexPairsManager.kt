package com.sedsoftware.exchange.bitfinex

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.core.domain.repository.PairsManagerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BitfinexPairsManager @Inject constructor(
    @ForExchange(BITFINEX) override val repository: PairsManagerRepository
) : CurrencyPairsManager
