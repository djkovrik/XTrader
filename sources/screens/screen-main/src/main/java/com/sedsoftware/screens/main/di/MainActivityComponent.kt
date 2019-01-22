package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.core.di.holder.NavControllerHolder
import com.sedsoftware.screens.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    dependencies = [
        AppProvider::class
    ],
    modules = [
        MainActivityModule::class,
        NavigationModule::class
    ]
)
interface MainActivityComponent : MainActivityToolsProvider {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        fun appProvider(appProvider: AppProvider): Builder

        @BindsInstance
        fun navControllerHolder(navControllerHolder: NavControllerHolder): Builder

        fun build(): MainActivityComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider, navControllerHolder: NavControllerHolder): MainActivityComponent {

                return DaggerMainActivityComponent.builder()
                    .appProvider(appProvider)
                    .navControllerHolder(navControllerHolder)
                    .build()
            }
        }
    }
}
