package com.sedsoftware.screens.intro.di

import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.intro.IntroScreenFragment
import dagger.Component

@Component(
    dependencies = [
        StartingFlowToolsProvider::class
    ]
)
@ScreenScope
interface IntroScreenComponent {

    fun inject(fragment: IntroScreenFragment)

    @Component.Factory
    interface Factory {
        fun create(startingFlowToolsProvider: StartingFlowToolsProvider): IntroScreenComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(startingFlowToolsProvider: StartingFlowToolsProvider): IntroScreenComponent {

                return DaggerIntroScreenComponent
                    .factory()
                    .create(startingFlowToolsProvider)
            }
        }
    }
}
