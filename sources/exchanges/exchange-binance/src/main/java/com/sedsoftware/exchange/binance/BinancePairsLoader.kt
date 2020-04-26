package com.sedsoftware.exchange.binance

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.domain.tools.NetworkHandler
import javax.inject.Inject

class BinancePairsLoader @Inject constructor(
    @ForExchange(BINANCE)
    override val repository: PairsInfoRepository,
    override val networkHandler: NetworkHandler
) : CurrencyPairsLoader
