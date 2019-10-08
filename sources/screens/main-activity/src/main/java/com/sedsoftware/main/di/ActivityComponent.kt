package com.sedsoftware.main.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.main.MainActivity
import dagger.BindsInstance
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
        fun create(
            appProvider: AppProvider,
            @BindsInstance snackbarDelegate: SnackbarDelegate
        ): ActivityComponent
    }

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider, snackbarDelegate: SnackbarDelegate): ActivityComponent {

                return DaggerActivityComponent
                    .factory()
                    .create(appProvider, snackbarDelegate)
            }
        }
    }
}
