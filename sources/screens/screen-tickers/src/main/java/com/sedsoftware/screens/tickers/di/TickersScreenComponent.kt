package com.sedsoftware.screens.tickers.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.tickers.ui.TickersScreenFragment
import dagger.Component

@Component(
    dependencies = [
        ActivityToolsProvider::class
    ],
    modules = [
        TickersScreenModule::class
    ]
)
@ScreenScope
interface TickersScreenComponent {

    fun inject(fragment: TickersScreenFragment)

    @Component.Factory
    interface Factory {
        fun create(provider: ActivityToolsProvider): TickersScreenComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(provider: ActivityToolsProvider): TickersScreenComponent {

                return DaggerTickersScreenComponent
                    .factory()
                    .create(provider)
            }
        }
    }
}
