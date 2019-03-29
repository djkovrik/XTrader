package com.sedsoftware.exchange.manager.provider

import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.exchange.manager.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeAssetsProvider @Inject constructor() : AssetsProvider {

    override fun provideLogo(exchange: Exchange): Int =
        when (exchange) {
            ExchangeType.BINANCE -> R.drawable.logo_binance
            else -> 0
        }
}
