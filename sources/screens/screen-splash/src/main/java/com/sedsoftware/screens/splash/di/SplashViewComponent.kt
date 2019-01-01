package com.sedsoftware.screens.splash.di

import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.scope.FragmentScope
import com.sedsoftware.screens.splash.SplashFragment
import dagger.Component

@FragmentScope
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

    @Component.Builder
    interface Builder {

        fun mainActivityToolsProvider(provider: MainActivityToolsProvider): Builder

        fun build(): SplashViewComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(mainActivityToolsProvider: MainActivityToolsProvider): SplashViewComponent {

                return DaggerSplashViewComponent.builder()
                    .mainActivityToolsProvider(mainActivityToolsProvider)
                    .build()
            }
        }
    }
}
