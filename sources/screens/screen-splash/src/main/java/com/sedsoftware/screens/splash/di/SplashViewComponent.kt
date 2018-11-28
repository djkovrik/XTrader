package com.sedsoftware.screens.splash.di

import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.scope.FragmentScope
import com.sedsoftware.core.navigation.holder.NavDirectionsFactoryHolder
import com.sedsoftware.screens.splash.SplashFragment
import dagger.BindsInstance
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

        @BindsInstance
        fun navDirectionsFactoryHolder(holder: NavDirectionsFactoryHolder): Builder

        fun build(): SplashViewComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(provider: MainActivityToolsProvider, holder: NavDirectionsFactoryHolder): SplashViewComponent {

                return DaggerSplashViewComponent.builder()
                    .mainActivityToolsProvider(provider)
                    .navDirectionsFactoryHolder(holder)
                    .build()
            }
        }
    }
}
