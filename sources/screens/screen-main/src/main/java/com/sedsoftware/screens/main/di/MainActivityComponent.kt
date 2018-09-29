package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.provider.ApplicationProvider
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.screens.main.MainActivity
import com.sedsoftware.screens.main.di.viewmodel.ViewModelFactoryProvider
import com.sedsoftware.screens.main.di.viewmodel.ViewModuleComponent
import dagger.Component

@ActivityScope
@Component(
    dependencies = [
        ApplicationProvider::class,
        ViewModelFactoryProvider::class
    ],
    modules = [
        MainActivityModule::class
    ]
)
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(appComponent: ApplicationProvider): MainActivityComponent {

                val viewModelFactoryProvider = ViewModuleComponent.Initializer.init()

                return DaggerMainActivityComponent.builder()
                    .applicationProvider(appComponent)
                    .viewModelFactoryProvider(viewModelFactoryProvider)
                    .build()
            }
        }
    }
}
