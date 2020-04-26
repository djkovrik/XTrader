package com.sedsoftware.exchange.manager.provider

import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.exchange.manager.R
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ExchangeAssetsProvider @Inject constructor() : AssetsProvider {

    override fun getLogoResource(exchange: Exchange): Int =
        when (exchange) {
            ExchangeType.BINANCE -> R.drawable.logo_binance
            ExchangeType.BITFINEX -> R.drawable.logo_bitfinex
            else -> 0
        }
}
