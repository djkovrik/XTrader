package com.sedsoftware.xtrader.di

import com.sedsoftware.core.di.provider.ApplicationProvider
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.device.di.DeviceToolsComponent
import com.sedsoftware.xtrader.XTraderApp
import dagger.Component
import javax.inject.Singleton

@Component(dependencies = [DeviceToolsProvider::class])
@Singleton
interface AppComponent : ApplicationProvider {

    fun inject(app: XTraderApp)

    class Initializer private constructor() {
        companion object {

            fun init(app: XTraderApp): AppComponent {

                val deviceToolsProvider = DeviceToolsComponent.Initializer.init(app)

                return DaggerAppComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .build()
            }
        }
    }
}
