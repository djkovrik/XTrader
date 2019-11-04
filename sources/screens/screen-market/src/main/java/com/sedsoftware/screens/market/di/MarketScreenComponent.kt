package com.sedsoftware.screens.market.di

import com.sedsoftware.core.di.RegularFlowToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.market.MarketScreenFragment
import dagger.Component

@Component(
    dependencies = [
        RegularFlowToolsProvider::class
    ]
)
@ScreenScope
interface MarketScreenComponent {

    fun inject(fragment: MarketScreenFragment)

    @Component.Factory
    interface Factory {
        fun create(regularFlowToolsProvider: RegularFlowToolsProvider): MarketScreenComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(regularFlowToolsProvider: RegularFlowToolsProvider): MarketScreenComponent {

                return DaggerMarketScreenComponent
                    .factory()
                    .create(regularFlowToolsProvider)
            }
        }
    }
}
