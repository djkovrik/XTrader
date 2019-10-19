package com.sedsoftware.main.flows.starting.di.module

import com.sedsoftware.core.di.qualifier.StartingFlow
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class StartingFlowModule {

    @Provides
    @StartingFlow
    fun provideCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @StartingFlow
    fun provideRouter(@StartingFlow cicerone: Cicerone<Router>): Router =
        cicerone.router

    @Provides
    @StartingFlow
    fun provideNavigatorHolder(@StartingFlow cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}
