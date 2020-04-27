package com.sedsoftware.screens.intro.exchanges.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesFragment
import com.sedsoftware.screens.intro.exchanges.view.adapter.ExchangeListAdapter
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        ActivityToolsProvider::class
    ]
)
@ScreenScope
interface IntroExchangesComponent {

    fun inject(fragment: IntroExchangesFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance clickListener: ExchangeListAdapter.Listener,
            activityToolsProvider: ActivityToolsProvider
        ): IntroExchangesComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(
                clickListener: ExchangeListAdapter.Listener,
                activityToolsProvider: ActivityToolsProvider
            ): IntroExchangesComponent {

                return DaggerIntroExchangesComponent
                    .factory()
                    .create(clickListener, activityToolsProvider)
            }
        }
    }
}
