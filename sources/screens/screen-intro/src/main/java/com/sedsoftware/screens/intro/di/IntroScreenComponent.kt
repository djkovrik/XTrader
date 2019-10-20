package com.sedsoftware.screens.intro.di

import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.intro.IntroScreenFragment
import com.sedsoftware.screens.intro.adapter.ExchangeListAdapter
import dagger.BindsInstance
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
        fun create(
            @BindsInstance clickListener: ExchangeListAdapter.Listener,
            startingFlowToolsProvider: StartingFlowToolsProvider
        ): IntroScreenComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(
                clickListener: ExchangeListAdapter.Listener,
                startingFlowToolsProvider: StartingFlowToolsProvider
            ): IntroScreenComponent {

                return DaggerIntroScreenComponent
                    .factory()
                    .create(clickListener, startingFlowToolsProvider)
            }
        }
    }
}
