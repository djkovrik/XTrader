package com.sedsoftware.exchange.coinmarketcap.di

import com.sedsoftware.core.di.CoinMarketCapProvider
import com.sedsoftware.core.di.DeviceToolsProvider
import com.sedsoftware.exchange.coinmarketcap.di.module.CurrencyDatabaseModule
import com.sedsoftware.exchange.coinmarketcap.di.module.CoinMarketCapModule
import com.sedsoftware.exchange.coinmarketcap.di.module.CurrencyNetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        DeviceToolsProvider::class
    ],
    modules = [
        CurrencyDatabaseModule::class,
        CurrencyNetworkModule::class,
        CoinMarketCapModule::class
    ]
)
@Singleton
interface CoinMarketCapComponent : CoinMarketCapProvider {
    class Initializer private constructor() {
        companion object {

            fun init(deviceToolsProvider: DeviceToolsProvider): CoinMarketCapProvider {
                return DaggerCoinMarketCapComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .build()
            }
        }
    }
}
