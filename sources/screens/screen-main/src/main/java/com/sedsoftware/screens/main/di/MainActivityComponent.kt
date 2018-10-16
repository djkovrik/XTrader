package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.scope.PerScreen
import com.sedsoftware.core.presentation.navigation.NavControllerHolder
import com.sedsoftware.screens.main.di.viewmodel.ViewModelFactoryProvider
import com.sedsoftware.screens.main.di.viewmodel.ViewModuleComponent
import dagger.BindsInstance
import dagger.Component

@PerScreen
@Component(
    dependencies = [
        AppProvider::class,
        ViewModelFactoryProvider::class
    ],
    modules = [
        MainActivityModule::class
    ]
)
interface MainActivityComponent : MainActivityToolsProvider {

    @Component.Builder
    interface Builder {

        fun appProvider(appProvider: AppProvider): Builder

        fun viewModelFactoryProvider(factoryProvider: ViewModelFactoryProvider): Builder

        @BindsInstance
        fun navControllerHolder(navControllerHolder: NavControllerHolder): Builder

        fun build(): MainActivityComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider, hostActivity: NavControllerHolder): MainActivityToolsProvider {

                val viewModelFactoryProvider = ViewModuleComponent.Initializer.init()

                return DaggerMainActivityComponent.builder()
                    .appProvider(appProvider)
                    .viewModelFactoryProvider(viewModelFactoryProvider)
                    .navControllerHolder(hostActivity)
                    .build()
            }
        }
    }
}
