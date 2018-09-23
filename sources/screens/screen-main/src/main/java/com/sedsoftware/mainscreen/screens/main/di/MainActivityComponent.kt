package com.sedsoftware.mainscreen.screens.main.di

import com.sedsoftware.coredi.provider.ApplicationProvider
import com.sedsoftware.coredi.scope.ActivityScope
import com.sedsoftware.mainscreen.screens.main.MainActivity
import com.sedsoftware.mainscreen.screens.main.di.viewmodel.ViewModelFactoryProvider
import com.sedsoftware.mainscreen.screens.main.di.viewmodel.ViewModuleComponent
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
