package com.sedsoftware.exchange.coinmarketcap

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.provider.CurrencyProvider
import com.sedsoftware.exchange.coinmarketcap.repository.CurrencyNameRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinMarketCapCurrencyProvider @Inject constructor(
    private val repository: CurrencyNameRepository
) : CurrencyProvider {

    override suspend fun getCurrency(symbol: String): Currency =
        repository.getCurrency(symbol)
}
