package com.sedsoftware.screen_main.di

import com.sedsoftware.core.di.provider.ApplicationProvider
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.screen_main.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationProvider::class],
    modules = [MainActivityModule::class]
)
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(appComponent: ApplicationProvider): MainActivityComponent =
                DaggerMainActivityComponent.builder()
                    .applicationProvider(appComponent)
                    .build()
        }
    }
}
