package com.sedsoftware.core.navigation.di

import com.sedsoftware.core.di.qualifier.Global
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationToolsModule {

    @Provides
    @Singleton
    @Global
    fun provideCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @Singleton
    @Global
    fun provideRouter(@Global cicerone: Cicerone<Router>): Router =
        cicerone.router

    @Provides
    @Singleton
    @Global
    fun provideNavigatorHolder(@Global cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}
