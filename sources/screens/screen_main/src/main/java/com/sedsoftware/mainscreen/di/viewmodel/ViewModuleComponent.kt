package com.sedsoftware.mainscreen.di.viewmodel

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelFactoryModule::class]
)
interface ViewModuleComponent : ViewModelFactoryProvider {
    class Initializer private constructor() {
        companion object {

            fun init(): ViewModelFactoryProvider =
                    DaggerViewModuleComponent.builder()
                        .build()
        }
    }
}
