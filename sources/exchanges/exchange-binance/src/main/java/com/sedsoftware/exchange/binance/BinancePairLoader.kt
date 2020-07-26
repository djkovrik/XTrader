package com.sedsoftware.exchange.binance

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.repository.PairInfoRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import javax.inject.Inject

class BinancePairLoader @Inject constructor(
    @ForExchange(BINANCE) override val repository: PairInfoRepository,
    override val networkHandler: NetworkHandler
) : CurrencyPairLoader
