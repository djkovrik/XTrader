package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        AppProvider::class
    ],
    modules = [
        ViewModelFactoryModule::class
    ]
)
interface ViewModelFactoryComponent : MainActivityToolsProvider {

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider): MainActivityToolsProvider {

                return DaggerViewModelFactoryComponent.builder()
                    .appProvider(appProvider)
                    .build()
            }
        }
    }
}
