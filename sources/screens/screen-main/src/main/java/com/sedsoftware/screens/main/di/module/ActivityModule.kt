package com.sedsoftware.screens.main.di.module

import com.sedsoftware.core.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @ActivityScope
    fun provideRouter(cicerone: Cicerone<Router>): Router =
        cicerone.router

    @Provides
    @ActivityScope
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}
