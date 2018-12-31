package com.sedsoftware.core.tools.impl.di

import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PlatformToolsModule::class,
        AppToolsModule::class
    ]
)
interface DeviceToolsComponent : DeviceToolsProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(app: App): Builder

        fun build(): DeviceToolsComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(app: App): DeviceToolsProvider =
                DaggerDeviceToolsComponent.builder()
                    .app(app)
                    .build()
        }
    }
}
