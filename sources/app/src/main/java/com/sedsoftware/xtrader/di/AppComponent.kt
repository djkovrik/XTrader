package com.sedsoftware.xtrader.di

import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.core.di.provider.ExchangeManagerProvider
import com.sedsoftware.core.di.provider.ViewModelFactoryProvider
import com.sedsoftware.core.tools.impl.di.DeviceToolsComponent
import com.sedsoftware.exchange.manager.di.ExchangeManagerComponent
import com.sedsoftware.screens.main.di.ViewModelFactoryComponent
import com.sedsoftware.xtrader.XTraderApp
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        DeviceToolsProvider::class,
        ExchangeManagerProvider::class,
        ViewModelFactoryProvider::class
    ]
)
@Singleton
interface AppComponent : AppProvider {

    fun inject(app: XTraderApp)

    class Initializer private constructor() {
        companion object {

            fun init(app: XTraderApp): AppComponent {

                val deviceToolsProvider =
                    DeviceToolsComponent.Initializer.init(app)

                val exchangeManagerProvider =
                    ExchangeManagerComponent.Initializer.init(deviceToolsProvider)

                val viewModelFactoryProvider =
                    ViewModelFactoryComponent.Initializer.init()

                return DaggerAppComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .exchangeManagerProvider(exchangeManagerProvider)
                    .viewModelFactoryProvider(viewModelFactoryProvider)
                    .build()
            }
        }
    }
}
