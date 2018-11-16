package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.holder.NavControllerHolder
import com.sedsoftware.core.di.provider.ActivityToolsProvider
import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.scope.PerScreen
import dagger.BindsInstance
import dagger.Component

@PerScreen
@Component(
    dependencies = [
        AppProvider::class
    ],
    modules = [
        MainActivityModule::class,
        ViewModelFactoryModule::class
    ]
)
interface MainActivityComponent : ActivityToolsProvider {

    @Component.Builder
    interface Builder {

        fun appProvider(appProvider: AppProvider): Builder

        @BindsInstance
        fun navControllerHolder(navControllerHolder: NavControllerHolder): Builder

        fun build(): MainActivityComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider, hostActivity: NavControllerHolder): ActivityToolsProvider {

                return DaggerMainActivityComponent.builder()
                    .appProvider(appProvider)
                    .navControllerHolder(hostActivity)
                    .build()
            }
        }
    }
}
