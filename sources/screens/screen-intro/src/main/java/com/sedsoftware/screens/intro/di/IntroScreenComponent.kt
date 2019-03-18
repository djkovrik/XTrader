package com.sedsoftware.screens.intro.di

import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.screens.intro.IntroScreenFragment
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
