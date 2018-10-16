package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.scope.PerScreen
import com.sedsoftware.core.presentation.navigation.NavControllerProvider
import com.sedsoftware.screens.main.MainActivity
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
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        fun appProvider(appProvider: AppProvider): Builder

        fun viewModelFactoryProvider(factoryProvider: ViewModelFactoryProvider): Builder

        @BindsInstance
        fun navControllerProvider(navControllerProvider: NavControllerProvider): Builder

        fun build(): MainActivityComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider, hostActivity: NavControllerProvider): MainActivityComponent {

                val viewModelFactoryProvider = ViewModuleComponent.Initializer.init()

                return DaggerMainActivityComponent.builder()
                    .appProvider(appProvider)
                    .viewModelFactoryProvider(viewModelFactoryProvider)
                    .navControllerProvider(hostActivity)
                    .build()
            }
        }
    }
}
