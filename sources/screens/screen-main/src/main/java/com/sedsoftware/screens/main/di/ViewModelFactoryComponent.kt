package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.CoinMarketCapProvider
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.core.di.provider.ExchangeManagerProvider
import com.sedsoftware.core.di.provider.ViewModelFactoryProvider
import com.sedsoftware.exchange.coinmarketcap.di.CoinMarketCapComponent
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        DeviceToolsProvider::class,
        ExchangeManagerProvider::class,
        CoinMarketCapProvider::class
    ],
    modules = [
        ViewModelFactoryModule::class
    ]
)
@Singleton
interface ViewModelFactoryComponent : ViewModelFactoryProvider {

    class Initializer private constructor() {
        companion object {

            fun init(
                deviceToolsProvider: DeviceToolsProvider,
                exchangeManagerProvider: ExchangeManagerProvider
            ): ViewModelFactoryProvider {

                val coinMarketCapProvider = CoinMarketCapComponent.Initializer.init(deviceToolsProvider)

                return DaggerViewModelFactoryComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .exchangeManagerProvider(exchangeManagerProvider)
                    .coinMarketCapProvider(coinMarketCapProvider)
                    .build()
            }
        }
    }
}
