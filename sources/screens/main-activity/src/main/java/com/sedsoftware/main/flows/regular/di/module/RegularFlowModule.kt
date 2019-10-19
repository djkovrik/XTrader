package com.sedsoftware.main.flows.regular.di.module

import com.sedsoftware.core.di.qualifier.RegularFlow
import com.sedsoftware.core.di.scope.FlowScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class RegularFlowModule {

    @Provides
    @RegularFlow
    fun provideCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @RegularFlow
    fun provideRouter(@RegularFlow cicerone: Cicerone<Router>): Router =
        cicerone.router

    @Provides
    @RegularFlow
    fun provideNavigatorHolder(@RegularFlow cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}
