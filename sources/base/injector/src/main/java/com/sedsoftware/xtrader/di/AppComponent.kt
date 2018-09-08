package com.sedsoftware.xtrader.di

import com.sedsoftware.core.di.provider.ApplicationProvider
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.core.di.provider.ExchangeManagerProvider
import com.sedsoftware.device.di.DeviceToolsComponent
import com.sedsoftware.exchangemanager.di.ExchangeManagerComponent
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
interface AppComponent : ApplicationProvider {

    fun inject(app: XTraderApp)

    class Initializer private constructor() {
        companion object {

            fun init(app: XTraderApp): AppComponent {

                val deviceToolsProvider = DeviceToolsComponent.Initializer.init(app)

                val exchangeManagerProvider = ExchangeManagerComponent.Initializer.init(deviceToolsProvider)

                return DaggerAppComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .exchangeManagerProvider(exchangeManagerProvider)
                    .build()
            }
        }
    }
}
