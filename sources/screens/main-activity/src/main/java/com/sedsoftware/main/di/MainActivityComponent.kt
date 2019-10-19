package com.sedsoftware.main.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.main.MainActivity
import com.sedsoftware.main.di.module.NavigationModule
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class
    ],
    modules = [
        NavigationModule::class
    ]
)
@ActivityScope
interface MainActivityComponent : ActivityToolsProvider {

    @Component.Factory
    interface Factory {
        fun create(
            appProvider: AppProvider,
            @BindsInstance snackbarDelegate: SnackbarDelegate
        ): MainActivityComponent
    }

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider, snackbarDelegate: SnackbarDelegate): MainActivityComponent {

                return DaggerMainActivityComponent
                    .factory()
                    .create(appProvider, snackbarDelegate)
            }
        }
    }
}
