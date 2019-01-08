package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.DestinationsProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DestinationsModule::class
    ]
)
interface DestinationsComponent : DestinationsProvider {

    class Initializer private constructor() {
        companion object {

            fun init(): DestinationsProvider {

                return DaggerDestinationsComponent.builder()
                    .build()
            }
        }
    }
}
