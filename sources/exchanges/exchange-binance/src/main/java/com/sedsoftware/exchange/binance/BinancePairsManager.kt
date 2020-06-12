package com.sedsoftware.exchange.binance

import com.sedsoftware.core.di.annotations.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.core.domain.repository.PairsManagerRepository
import javax.inject.Inject

class BinancePairsManager @Inject constructor(
    @ForExchange(BINANCE) override val repository: PairsManagerRepository
) : CurrencyPairsManager
