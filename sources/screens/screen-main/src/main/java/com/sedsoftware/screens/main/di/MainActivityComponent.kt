package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.provider.ViewModelFactoryProvider
import com.sedsoftware.core.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(
    dependencies = [
        ViewModelFactoryProvider::class
    ],
    modules = [
        MainActivityModule::class
    ]
)
interface MainActivityComponent : MainActivityToolsProvider {

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider): MainActivityToolsProvider {

                val viewModelFactoryProvider = ViewModelFactoryComponent.Initializer.init(appProvider)

                return DaggerMainActivityComponent.builder()
                    .viewModelFactoryProvider(viewModelFactoryProvider)
                    .build()
            }
        }
    }
}
