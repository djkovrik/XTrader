package com.sedsoftware.exchange.coinmarketcap.di

import com.sedsoftware.core.di.CoinMarketCapProvider
import com.sedsoftware.core.di.DeviceToolsProvider
import com.sedsoftware.exchange.coinmarketcap.di.module.CoinMarketCapExportModule
import com.sedsoftware.exchange.coinmarketcap.di.module.CoinMarketCapRepositoryModule
import com.sedsoftware.exchange.coinmarketcap.di.module.CoinMarketCapDatabaseModule
import com.sedsoftware.exchange.coinmarketcap.di.module.CoinMarketCapNetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        DeviceToolsProvider::class
    ],
    modules = [
        CoinMarketCapDatabaseModule::class,
        CoinMarketCapNetworkModule::class,
        CoinMarketCapRepositoryModule::class,
        CoinMarketCapExportModule::class
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
