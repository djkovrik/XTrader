package com.sedsoftware.coredeviceimpl.di

import com.sedsoftware.coredi.App
import com.sedsoftware.coredi.provider.DeviceToolsProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PlatformToolsModule::class,
        DeviceToolsModule::class
    ]
)
interface DeviceToolsComponent : DeviceToolsProvider {

    @Component.Builder
    interface Builder {

        fun build(): DeviceToolsComponent

        @BindsInstance
        fun app(app: App): Builder
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
