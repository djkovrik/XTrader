package com.sedsoftware.device.di

import com.sedsoftware.core.App
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DeviceToolsModule::class])
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
