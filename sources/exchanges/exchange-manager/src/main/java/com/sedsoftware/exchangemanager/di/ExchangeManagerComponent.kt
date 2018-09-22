package com.sedsoftware.exchangemanager.di

import com.sedsoftware.binance.di.BinanceComponent
import com.sedsoftware.binance.di.BinanceProvider
import com.sedsoftware.coredi.provider.DeviceToolsProvider
import com.sedsoftware.coredi.provider.ExchangeManagerProvider
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
