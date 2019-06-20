package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.screens.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class,
        NavigationProvider::class
    ],
    modules = [
        MainActivityModule::class
    ]
)
@ActivityScope
interface MainActivityComponent : MainActivityToolsProvider {

    @Component.Builder
    interface Builder {

        fun appProvider(appProvider: AppProvider): Builder

        fun navigationProvider(appProvider: NavigationProvider): Builder

        @BindsInstance
        fun snackbarDelegate(snackbarDelegate: SnackbarDelegate): Builder

        fun build(): MainActivityComponent
    }

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider, snackbarDelegate: SnackbarDelegate): MainActivityComponent {

                val navigationProvider =
                    NavigationComponent.Initializer.init()

                return DaggerMainActivityComponent.builder()
                    .appProvider(appProvider)
                    .navigationProvider(navigationProvider)
                    .snackbarDelegate(snackbarDelegate)
                    .build()
            }
        }
    }
}
