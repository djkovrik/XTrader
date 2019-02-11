package com.sedsoftware.screens.intro.di

import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.screens.intro.IntroDownloadsFragment
import com.sedsoftware.screens.intro.IntroGreetingsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        MainActivityToolsProvider::class
    ],
    modules = [
        IntroScreenModule::class
    ]
)
interface IntroScreenComponent {

    fun inject(fragment: IntroGreetingsFragment)
    fun inject(fragment: IntroDownloadsFragment)

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
