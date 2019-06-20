package com.sedsoftware.screens.main.di

import com.sedsoftware.screens.main.navigation.NavControllerHolder
import com.sedsoftware.screens.main.navigation.Navigator
import com.sedsoftware.screens.main.navigation.Router
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
