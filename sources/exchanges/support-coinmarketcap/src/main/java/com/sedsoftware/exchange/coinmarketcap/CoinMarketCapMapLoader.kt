package com.sedsoftware.exchange.coinmarketcap

import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.core.domain.repository.CurrencyMapRepository
import com.sedsoftware.core.tools.api.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinMarketCapMapLoader @Inject constructor(
    override val repository: CurrencyMapRepository,
    override val networkHandler: NetworkHandler
) : CurrencyMapLoader
