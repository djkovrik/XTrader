package com.sedsoftware.screens.startup.di

import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.scope.FragmentScope
import com.sedsoftware.screens.startup.StartupFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [
        MainActivityToolsProvider::class
    ]
)
interface StartupViewComponent {

    fun inject(fragment: StartupFragment)

    class Initializer private constructor() {
        companion object {

            fun init(mainActivityToolsProvider: MainActivityToolsProvider): StartupViewComponent {

                return DaggerStartupViewComponent.builder()
                    .mainActivityToolsProvider(mainActivityToolsProvider)
                    .build()
            }
        }
    }
}
