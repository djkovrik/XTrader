package com.sedsoftware.screens.market.di

import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.screens.market.MarketScreenFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        MainActivityToolsProvider::class
    ]
)
@Singleton
interface MarketScreenComponent {

    fun inject(fragment: MarketScreenFragment)

    @Component.Factory
    interface Factory {
        fun create(mainActivityToolsProvider: MainActivityToolsProvider): MarketScreenComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(mainActivityToolsProvider: MainActivityToolsProvider): MarketScreenComponent {

                return DaggerMarketScreenComponent
                    .factory()
                    .create(mainActivityToolsProvider)
            }
        }
    }
}
