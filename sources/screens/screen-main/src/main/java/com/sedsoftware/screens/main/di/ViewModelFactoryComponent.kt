package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.CoinMarketCapProvider
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.core.di.provider.ExchangeManagerProvider
import com.sedsoftware.core.di.provider.ViewModelFactoryProvider
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        DeviceToolsProvider::class,
        CoinMarketCapProvider::class,
        ExchangeManagerProvider::class
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
                coinMarketCapProvider: CoinMarketCapProvider,
                exchangeManagerProvider: ExchangeManagerProvider
            ): ViewModelFactoryProvider {

                return DaggerViewModelFactoryComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .coinMarketCapProvider(coinMarketCapProvider)
                    .exchangeManagerProvider(exchangeManagerProvider)
                    .build()
            }
        }
    }
}
