package com.sedsoftware.main.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.main.MainActivity
import com.sedsoftware.main.di.module.NavigationModule
import com.sedsoftware.main.di.module.CiceroneModule
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class
    ],
    modules = [
        CiceroneModule::class,
        NavigationModule::class
    ]
)
@ActivityScope
interface MainActivityComponent : ActivityToolsProvider {

    @Component.Factory
    interface Factory {
        fun create(appProvider: AppProvider): MainActivityComponent
    }

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider): MainActivityComponent {

                return DaggerMainActivityComponent
                    .factory()
                    .create(appProvider)
            }
        }
    }
}
