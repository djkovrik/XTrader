package com.sedsoftware.screens.intro.di

import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.di.scope.PerScreen
import com.sedsoftware.screens.intro.IntroScreenFragment
import dagger.Component

@PerScreen
@Component(
    dependencies = [
        MainActivityToolsProvider::class
    ],
    modules = [
        IntroScreenModule::class
    ]
)
interface IntroScreenComponent {

    fun inject(fragment: IntroScreenFragment)

    class Initializer private constructor() {
        companion object {

            fun init(activityToolsProvider: MainActivityToolsProvider): IntroScreenComponent {

                return DaggerIntroScreenComponent.builder()
                    .mainActivityToolsProvider(activityToolsProvider)
                    .build()
            }
        }
    }
}
