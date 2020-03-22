package com.sedsoftware.exchange.coinmarketcap

import com.sedsoftware.core.domain.interactor.CurrencyManager
import com.sedsoftware.core.domain.repository.CurrencyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinMarketCapCurrencyManager @Inject constructor(
    override val repository: CurrencyRepository
) : CurrencyManager
