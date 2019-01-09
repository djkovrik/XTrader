package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.DestinationsProvider
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.core.di.provider.NavigationProvider
import com.sedsoftware.core.di.provider.ViewModelFactoryProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        DestinationsProvider::class,
        DeviceToolsProvider::class,
        NavigationProvider::class
    ],
    modules = [
        ViewModelFactoryModule::class
    ]
)
interface ViewModelFactoryComponent : ViewModelFactoryProvider {

    class Initializer private constructor() {
        companion object {

            fun init(
                destinationsProvider: DestinationsProvider,
                deviceToolsProvider: DeviceToolsProvider,
                navigationProvider: NavigationProvider
            ): ViewModelFactoryProvider {

                return DaggerViewModelFactoryComponent.builder()
                    .destinationsProvider(destinationsProvider)
                    .deviceToolsProvider(deviceToolsProvider)
                    .navigationProvider(navigationProvider)
                    .build()
            }
        }
    }
}
