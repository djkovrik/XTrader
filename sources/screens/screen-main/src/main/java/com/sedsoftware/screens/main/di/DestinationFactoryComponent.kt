package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.DestinationFactoryProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DestinationFactoryModule::class
    ]
)
interface DestinationFactoryComponent : DestinationFactoryProvider {

    class Initializer private constructor() {
        companion object {

            fun init(): DestinationFactoryProvider {

                return DaggerDestinationFactoryComponent.builder()
                    .build()
            }
        }
    }
}
