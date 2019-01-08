package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(
    dependencies = [
        AppProvider::class
    ]
)
interface MainActivityComponent : MainActivityToolsProvider {

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider): MainActivityToolsProvider {

                return DaggerMainActivityComponent.builder()
                    .appProvider(appProvider)
                    .build()
            }
        }
    }
}
