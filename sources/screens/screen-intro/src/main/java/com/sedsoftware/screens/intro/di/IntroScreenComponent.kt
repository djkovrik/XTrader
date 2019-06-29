package com.sedsoftware.screens.intro.di

import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.screens.intro.IntroScreenFragment
import com.sedsoftware.screens.intro.adapter.ExchangesAdapter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        MainActivityToolsProvider::class
    ]
)
@Singleton
interface IntroScreenComponent {

    fun inject(fragment: IntroScreenFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance clickListener: ExchangesAdapter.Listener,
                   mainActivityToolsProvider: MainActivityToolsProvider
        ): IntroScreenComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(clickListener: ExchangesAdapter.Listener,
                     mainActivityToolsProvider: MainActivityToolsProvider
            ): IntroScreenComponent {

                return DaggerIntroScreenComponent
                        .factory()
                        .create(clickListener, mainActivityToolsProvider)
            }
        }
    }
}
