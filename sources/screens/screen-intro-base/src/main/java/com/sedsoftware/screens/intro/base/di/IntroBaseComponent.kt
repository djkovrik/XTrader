package com.sedsoftware.screens.intro.base.di

import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.intro.base.IntroBaseFragment
import dagger.Component

@Component(
    dependencies = [
        StartingFlowToolsProvider::class
    ]
)
@ScreenScope
interface IntroBaseComponent {

    fun inject(fragment: IntroBaseFragment)

    @Component.Factory
    interface Factory {
        fun create(
            startingFlowToolsProvider: StartingFlowToolsProvider
        ): IntroBaseComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(
                startingFlowToolsProvider: StartingFlowToolsProvider
            ): IntroBaseComponent {

                return DaggerIntroBaseComponent
                    .factory()
                    .create(startingFlowToolsProvider)
            }
        }
    }
}
