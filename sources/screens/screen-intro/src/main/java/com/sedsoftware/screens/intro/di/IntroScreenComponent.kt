package com.sedsoftware.screens.intro.di

import com.sedsoftware.core.di.FlowToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.intro.IntroScreenFragment
import com.sedsoftware.screens.intro.adapter.ExchangeListAdapter
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        FlowToolsProvider::class
    ]
)
@ScreenScope
interface IntroScreenComponent {

    fun inject(fragment: IntroScreenFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance clickListener: ExchangeListAdapter.Listener,
            flowToolsProvider: FlowToolsProvider
        ): IntroScreenComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(
                clickListener: ExchangeListAdapter.Listener,
                flowToolsProvider: FlowToolsProvider
            ): IntroScreenComponent {

                return DaggerIntroScreenComponent
                    .factory()
                    .create(clickListener, flowToolsProvider)
            }
        }
    }
}
