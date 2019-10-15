package com.sedsoftware.main.flows.di

import com.sedsoftware.core.di.scope.FlowScope
import com.sedsoftware.main.flows.Flows
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Named

@Module
class StartingFlowModule {

    @Provides
    @FlowScope
    @Named(Flows.STARTING)
    fun provideCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @FlowScope
    @Named(Flows.STARTING)
    fun provideRouter(cicerone: Cicerone<Router>): Router =
        cicerone.router

    @Provides
    @FlowScope
    @Named(Flows.STARTING)
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}
