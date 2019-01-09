package com.sedsoftware.screens.main.di

import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.navigation.XTNavigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideRouter(xtNavigator: XTNavigator): Router =
        xtNavigator.getRouter()

    @Provides
    @Singleton
    fun provideNavControllerHolder(xtNavigator: XTNavigator): NavControllerHolder =
        xtNavigator.getNavControllerHolder()
}
