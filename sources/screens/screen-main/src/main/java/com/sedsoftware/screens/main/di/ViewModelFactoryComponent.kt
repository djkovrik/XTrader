package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.core.di.provider.ExchangeManagerProvider
import com.sedsoftware.core.di.provider.ViewModelFactoryProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        DeviceToolsProvider::class,
        ExchangeManagerProvider::class
    ],
    modules = [
        ViewModelFactoryModule::class
    ]
)
interface ViewModelFactoryComponent : ViewModelFactoryProvider {

    class Initializer private constructor() {
        companion object {

            fun init(
                deviceToolsProvider: DeviceToolsProvider,
                exchangeManagerProvider: ExchangeManagerProvider
            ): ViewModelFactoryProvider {

                return DaggerViewModelFactoryComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .exchangeManagerProvider(exchangeManagerProvider)
                    .build()
            }
        }
    }
}
