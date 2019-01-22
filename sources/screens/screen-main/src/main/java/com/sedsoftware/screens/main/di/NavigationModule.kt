package com.sedsoftware.screens.main.di

import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideRouter(navigator: Navigator): Router =
        navigator.getRouter()

    @Provides
    @Singleton
    fun provideNavControllerHolder(navigator: Navigator): NavControllerHolder =
        navigator.getNavControllerHolder()
}
