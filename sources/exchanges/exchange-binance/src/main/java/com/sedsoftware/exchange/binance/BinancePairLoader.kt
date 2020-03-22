package com.sedsoftware.exchange.binance

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.tools.api.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinancePairLoader @Inject constructor(
    @ForExchange(BINANCE)
    override val repository: PairsInfoRepository,
    override val networkHandler: NetworkHandler
) : CurrencyPairLoader
