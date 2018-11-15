package com.sedsoftware.screens.main.di.viewmodel

import com.sedsoftware.core.di.provider.AppProvider
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
interface ViewModuleComponent : ViewModelFactoryProvider {
    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider): ViewModelFactoryProvider =
                DaggerViewModuleComponent.builder()
                    .appProvider(appProvider)
                    .build()
        }
    }
}
