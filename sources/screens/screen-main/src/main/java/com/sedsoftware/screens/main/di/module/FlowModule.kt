package com.sedsoftware.screens.main.di.module

import com.sedsoftware.core.di.scope.FlowScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class FlowModule {

    @Provides
    @FlowScope
    fun provideCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @FlowScope
    fun provideRouter(cicerone: Cicerone<Router>): Router =
        cicerone.router

    @Provides
    @FlowScope
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}
