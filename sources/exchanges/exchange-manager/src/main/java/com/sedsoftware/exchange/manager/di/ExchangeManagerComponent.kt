package com.sedsoftware.exchange.manager.di

import com.sedsoftware.exchange.binance.di.BinanceComponent
import com.sedsoftware.exchange.binance.di.BinanceProvider
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.core.di.provider.ExchangeManagerProvider
import dagger.Component

@Component(
    dependencies = [
        BinanceProvider::class
    ],
    modules = [
        ExchangeManagerModule::class
    ]
)
interface ExchangeManagerComponent : ExchangeManagerProvider {
    class Initializer private constructor() {
        companion object {

            fun init(deviceToolsProvider: DeviceToolsProvider): ExchangeManagerProvider {

                val binanceProvider = BinanceComponent.Initializer.init(deviceToolsProvider)

                return DaggerExchangeManagerComponent.builder()
                    .binanceProvider(binanceProvider)
                    .build()
            }
        }
    }
}
