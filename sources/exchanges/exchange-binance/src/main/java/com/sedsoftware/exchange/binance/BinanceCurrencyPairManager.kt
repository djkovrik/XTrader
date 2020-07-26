package com.sedsoftware.exchange.binance

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.domain.repository.PairManagerRepository
import javax.inject.Inject

class BinanceCurrencyPairManager @Inject constructor(
    @ForExchange(BINANCE) override val repository: PairManagerRepository
) : CurrencyPairManager
