package com.sedsoftware.exchange.manager.di

import com.sedsoftware.core.di.BinanceProvider
import com.sedsoftware.core.di.BitfinexProvider
import com.sedsoftware.core.di.CoinMarketCapProvider
import com.sedsoftware.core.di.DeviceToolsProvider
import com.sedsoftware.core.di.ExchangeManagerProvider
import com.sedsoftware.exchange.binance.di.BinanceComponent
import com.sedsoftware.exchange.bitfinex.di.BitfinexComponent
import com.sedsoftware.exchange.manager.di.module.ExchangeManagerModule
import com.sedsoftware.exchange.manager.di.module.ExchangeToolsModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        BinanceProvider::class,
        BitfinexProvider::class
    ],
    modules = [
        ExchangeManagerModule::class,
        ExchangeToolsModule::class
    ]
)
@Singleton
interface ExchangeManagerComponent : ExchangeManagerProvider {
    class Initializer private constructor() {
        companion object {

            fun init(
                deviceToolsProvider: DeviceToolsProvider,
                coinMarketCapProvider: CoinMarketCapProvider
            ): ExchangeManagerProvider {

                // All exchange providers here
                val binanceProvider = BinanceComponent.Initializer.init(deviceToolsProvider, coinMarketCapProvider)
                val bitfinexProvider = BitfinexComponent.Initializer.init(deviceToolsProvider, coinMarketCapProvider)

                return DaggerExchangeManagerComponent.builder()
                    .binanceProvider(binanceProvider)
                    .bitfinexProvider(bitfinexProvider)
                    .build()
            }
        }
    }
}
