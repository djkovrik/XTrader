package com.sedsoftware.mainscreen.di

import com.sedsoftware.core.di.provider.ApplicationProvider
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.mainscreen.MainActivity
import com.sedsoftware.mainscreen.di.viewmodel.ViewModelFactoryProvider
import com.sedsoftware.mainscreen.di.viewmodel.ViewModuleComponent
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
