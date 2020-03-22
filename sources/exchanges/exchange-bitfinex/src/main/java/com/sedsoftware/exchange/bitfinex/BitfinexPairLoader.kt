package com.sedsoftware.exchange.bitfinex

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.tools.api.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BitfinexPairLoader @Inject constructor(
    @ForExchange(BITFINEX)
    override val repository: PairsInfoRepository,
    override val networkHandler: NetworkHandler
) : CurrencyPairLoader
