package com.sedsoftware.screens.intro.exchanges.di

import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.intro.exchanges.IntroExchangesFragment
import com.sedsoftware.screens.intro.exchanges.adapter.ExchangeListAdapter
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        StartingFlowToolsProvider::class
    ]
)
@ScreenScope
interface IntroExchangesComponent {

    fun inject(fragment: IntroExchangesFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance clickListener: ExchangeListAdapter.Listener,
            startingFlowToolsProvider: StartingFlowToolsProvider
        ): IntroExchangesComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(
                clickListener: ExchangeListAdapter.Listener,
                startingFlowToolsProvider: StartingFlowToolsProvider
            ): IntroExchangesComponent {

                return DaggerIntroExchangesComponent
                    .factory()
                    .create(clickListener, startingFlowToolsProvider)
            }
        }
    }
}
