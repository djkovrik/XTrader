package com.sedsoftware.exchange.binance

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.domain.repository.PairsManagerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinancePairManager @Inject constructor(
    @ForExchange(BINANCE) override val repository: PairsManagerRepository
) : CurrencyPairManager
