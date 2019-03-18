package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.screens.main.MainActivity
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class
    ],
    modules = [
        MainActivityModule::class
    ]
)
@ActivityScope
interface MainActivityComponent : MainActivityToolsProvider {

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider): MainActivityComponent {

                return DaggerMainActivityComponent.builder()
                    .appProvider(appProvider)
                    .build()
            }
        }
    }
}
