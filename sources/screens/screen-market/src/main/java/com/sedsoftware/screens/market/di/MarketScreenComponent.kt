package com.sedsoftware.screens.market.di

import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.screens.market.MarketScreenFragment
import com.sedsoftware.screens.market.adapter.CurrencyListAdapter
import dagger.BindsInstance
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
        fun create(@BindsInstance clickListener: CurrencyListAdapter.Listener,
                   mainActivityToolsProvider: MainActivityToolsProvider
        ): MarketScreenComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(clickListener: CurrencyListAdapter.Listener,
                     mainActivityToolsProvider: MainActivityToolsProvider
            ): MarketScreenComponent {

                return DaggerMarketScreenComponent
                    .factory()
                    .create(clickListener, mainActivityToolsProvider)
            }
        }
    }
}
