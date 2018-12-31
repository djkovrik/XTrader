package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.ViewModelFactoryProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class
    ]
)
interface ViewModelFactoryComponent : ViewModelFactoryProvider {

    class Initializer private constructor() {
        companion object {

            fun init(): ViewModelFactoryProvider {

                return DaggerViewModelFactoryComponent.builder()
                    .build()
            }
        }
    }
}
