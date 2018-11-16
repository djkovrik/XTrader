package com.sedsoftware.screens.splash.di

import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.scope.PerScreen
import com.sedsoftware.screens.splash.SplashFragment
import dagger.Component

@PerScreen
@Component(
    dependencies = [
        MainActivityToolsProvider::class
    ],
    modules = [
        SplashViewModule::class
    ]
)
interface SplashViewComponent {

    fun inject(fragment: SplashFragment)

    class Initializer private constructor() {
        companion object {

            fun init(activityToolsProvider: MainActivityToolsProvider): SplashViewComponent {

                return DaggerSplashViewComponent.builder()
                    .mainActivityToolsProvider(activityToolsProvider)
                    .build()
            }
        }
    }
}
