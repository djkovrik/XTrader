package com.sedsoftware.screens.main.di

import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.navigation.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideNavControllerHolder(router: Router): NavControllerHolder =
        router.destinationsBuffer
}
