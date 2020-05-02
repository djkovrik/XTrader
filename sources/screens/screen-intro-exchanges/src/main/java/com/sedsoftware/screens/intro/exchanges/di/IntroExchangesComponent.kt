package com.sedsoftware.screens.intro.exchanges.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesFragment
import dagger.Component

@Component(
    dependencies = [
        ActivityToolsProvider::class
    ],
    modules = [
        IntroExchangesModule::class
    ]
)
@ScreenScope
interface IntroExchangesComponent {

    fun inject(fragment: IntroExchangesFragment)

    @Component.Factory
    interface Factory {
        fun create(provider: ActivityToolsProvider): IntroExchangesComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(provider: ActivityToolsProvider): IntroExchangesComponent {

                return DaggerIntroExchangesComponent
                    .factory()
                    .create(provider)
            }
        }
    }
}
