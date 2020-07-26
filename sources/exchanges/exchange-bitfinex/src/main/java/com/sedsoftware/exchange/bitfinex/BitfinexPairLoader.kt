package com.sedsoftware.exchange.bitfinex

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.repository.PairInfoRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import javax.inject.Inject

class BitfinexPairLoader @Inject constructor(
    @ForExchange(BITFINEX)
    override val repository: PairInfoRepository,
    override val networkHandler: NetworkHandler
) : CurrencyPairLoader
