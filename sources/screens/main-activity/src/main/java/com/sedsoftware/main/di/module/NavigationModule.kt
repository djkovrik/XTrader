package com.sedsoftware.main.di.module

import com.sedsoftware.core.di.qualifier.Global
import com.sedsoftware.core.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class NavigationModule {

    @Provides
    @ActivityScope
    @Global
    fun provideCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @ActivityScope
    @Global
    fun provideRouter(@Global cicerone: Cicerone<Router>): Router =
        cicerone.router

    @Provides
    @ActivityScope
    @Global
    fun provideNavigatorHolder(@Global cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}
