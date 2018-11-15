package com.sedsoftware.screens.splash.di

import com.sedsoftware.core.di.provider.ActivityToolsProvider
import com.sedsoftware.core.di.scope.PerScreen
import com.sedsoftware.screens.splash.SplashFragment
import dagger.Component

@PerScreen
@Component(
    dependencies = [
        ActivityToolsProvider::class
    ],
    modules = [
        SplashViewModule::class
    ]
)
interface SplashViewComponent {

    fun inject(fragment: SplashFragment)

    class Initializer private constructor() {
        companion object {

            fun init(activityToolsProvider: ActivityToolsProvider): SplashViewComponent {

                return DaggerSplashViewComponent.builder()
                    .activityToolsProvider(activityToolsProvider)
                    .build()
            }
        }
    }
}
