package com.sedsoftware.xtrader.di

import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.core.di.DeviceToolsProvider
import com.sedsoftware.core.di.ExchangeManagerProvider
import com.sedsoftware.core.di.NavigationProvider
import com.sedsoftware.core.di.ViewModelFactoryProvider
import com.sedsoftware.core.tools.impl.di.DeviceToolsComponent
import com.sedsoftware.exchange.coinmarketcap.di.CoinMarketCapComponent
import com.sedsoftware.exchange.manager.di.ExchangeManagerComponent
import com.sedsoftware.screens.main.di.NavigationComponent
import com.sedsoftware.screens.main.di.ViewModelFactoryComponent
import com.sedsoftware.xtrader.XTraderApp
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        DeviceToolsProvider::class,
        ExchangeManagerProvider::class
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

                val coinMarketCapProvider =
                    CoinMarketCapComponent.Initializer.init(deviceToolsProvider)

                val exchangeManagerProvider =
                    ExchangeManagerComponent.Initializer.init(deviceToolsProvider, coinMarketCapProvider)

                val viewModelFactoryProvider =
                    ViewModelFactoryComponent.Initializer
                        .init(deviceToolsProvider, coinMarketCapProvider, exchangeManagerProvider)

                val navigationProvider =
                    NavigationComponent.Initializer
                        .init()

                return DaggerAppComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .viewModelFactoryProvider(viewModelFactoryProvider)
                    .navigationProvider(navigationProvider)
                    .build()
            }
        }
    }
}
