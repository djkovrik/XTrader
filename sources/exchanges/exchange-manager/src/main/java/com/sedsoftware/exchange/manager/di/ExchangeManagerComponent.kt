package com.sedsoftware.exchange.manager.di

import com.sedsoftware.core.di.provider.BinanceProvider
import com.sedsoftware.core.di.provider.CoinMarketCapProvider
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.core.di.provider.ExchangeManagerProvider
import com.sedsoftware.exchange.binance.di.BinanceComponent
import com.sedsoftware.exchange.coinmarketcap.di.CoinMarketCapComponent
import com.sedsoftware.exchange.manager.di.module.ExchangeManagerModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        BinanceProvider::class,
        CoinMarketCapProvider::class
    ],
    modules = [
        ExchangeManagerModule::class
    ]
)
@Singleton
interface ExchangeManagerComponent : ExchangeManagerProvider {
    class Initializer private constructor() {
        companion object {

            fun init(toolsProvider: DeviceToolsProvider): ExchangeManagerProvider {

                val binanceProvider = BinanceComponent.Initializer.init(toolsProvider)
                val coinMarketCapProvider = CoinMarketCapComponent.Initializer.init(toolsProvider)

                return DaggerExchangeManagerComponent.builder()
                    .binanceProvider(binanceProvider)
                    .coinMarketCapProvider(coinMarketCapProvider)
                    .build()
            }
        }
    }
}
