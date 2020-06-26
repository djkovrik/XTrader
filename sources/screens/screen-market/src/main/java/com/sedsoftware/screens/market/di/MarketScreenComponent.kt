package com.sedsoftware.screens.market.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.market.ui.MarketScreenFragment
import dagger.Component

@Component(
    dependencies = [
        ActivityToolsProvider::class
    ],
    modules = [
        MarketScreenModule::class
    ]
)
@ScreenScope
interface MarketScreenComponent {

    fun inject(fragment: MarketScreenFragment)

    @Component.Factory
    interface Factory {
        fun create(provider: ActivityToolsProvider): MarketScreenComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(provider: ActivityToolsProvider): MarketScreenComponent {

                return DaggerMarketScreenComponent
                    .factory()
                    .create(provider)
            }
        }
    }
}
