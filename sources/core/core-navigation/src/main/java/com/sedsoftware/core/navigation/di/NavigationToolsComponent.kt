package com.sedsoftware.core.navigation.di

import com.sedsoftware.core.di.NavigationToolsProvider
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NavigationToolsModule::class
    ]
)
@Singleton
interface NavigationToolsComponent : NavigationToolsProvider {

    class Initializer private constructor() {
        companion object {

            fun init(): NavigationToolsProvider =
                DaggerNavigationToolsComponent.builder()
                    .build()
        }
    }
}
