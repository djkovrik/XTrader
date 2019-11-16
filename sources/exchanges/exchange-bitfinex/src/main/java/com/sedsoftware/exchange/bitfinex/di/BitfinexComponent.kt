package com.sedsoftware.exchange.bitfinex.di

import com.sedsoftware.core.di.BitfinexProvider
import com.sedsoftware.core.di.CoinMarketCapProvider
import com.sedsoftware.core.di.DeviceToolsProvider
import com.sedsoftware.exchange.bitfinex.di.module.BitfinexDatabaseModule
import com.sedsoftware.exchange.bitfinex.di.module.BitfinexNetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        DeviceToolsProvider::class,
        CoinMarketCapProvider::class
    ],
    modules = [
        BitfinexDatabaseModule::class,
        BitfinexNetworkModule::class
    ]
)
@Singleton
interface BitfinexComponent : BitfinexProvider {
    class Initializer private constructor() {
        companion object {

            fun init(
                deviceToolsProvider: DeviceToolsProvider,
                coinMarketCapProvider: CoinMarketCapProvider
            ): BitfinexProvider {
                return DaggerBitfinexComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .coinMarketCapProvider(coinMarketCapProvider)
                    .build()
            }
        }
    }
}
