package com.sedsoftware.exchange.coinmarketcap.di

import com.sedsoftware.core.di.provider.CoinMarketCapProvider
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.exchange.coinmarketcap.di.module.CurrencyDatabaseModule
import com.sedsoftware.exchange.coinmarketcap.di.module.CurrencyLoaderModule
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
        CurrencyLoaderModule::class
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