package com.sedsoftware.screens.intro.base.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.screens.intro.base.view.IntroBaseFragment
import dagger.Component

@Component(
    dependencies = [
        ActivityToolsProvider::class
    ],
    modules = [
        IntroBaseModule::class
    ]
)
@ScreenScope
interface IntroBaseComponent {

    fun inject(fragment: IntroBaseFragment)

    @Component.Factory
    interface Factory {
        fun create(provider: ActivityToolsProvider): IntroBaseComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(provider: ActivityToolsProvider): IntroBaseComponent {

                return DaggerIntroBaseComponent
                    .factory()
                    .create(provider)
            }
        }
    }
}
