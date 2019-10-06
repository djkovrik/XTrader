package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.screens.main.MainActivity
import com.sedsoftware.screens.main.di.module.ActivityModule
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class
    ],
    modules = [
        ActivityModule::class
    ]
)
@ActivityScope
interface ActivityComponent : ActivityToolsProvider {

    @Component.Factory
    interface Factory {
        fun create(appProvider: AppProvider): ActivityComponent
    }

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider): ActivityComponent {

                return DaggerActivityComponent
                    .factory()
                    .create(appProvider)
            }
        }
    }
}
