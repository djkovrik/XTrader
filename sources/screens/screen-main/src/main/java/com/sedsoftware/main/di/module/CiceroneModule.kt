package com.sedsoftware.main.di.module

import com.sedsoftware.core.di.qualifier.Global
import com.sedsoftware.core.di.qualifier.RegularFlow
import com.sedsoftware.core.di.qualifier.StartingFlow
import com.sedsoftware.core.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class CiceroneModule {

    @Provides
    @ActivityScope
    @Global
    fun provideGlobalCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @ActivityScope
    @Global
    fun provideGlobalRouter(@Global cicerone: Cicerone<Router>): Router =
        cicerone.router

    @Provides
    @ActivityScope
    @Global
    fun provideGlobalNavigatorHolder(@Global cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder


    @Provides
    @ActivityScope
    @StartingFlow
    fun provideStartingCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @ActivityScope
    @StartingFlow
    fun provideStartingRouter(@StartingFlow cicerone: Cicerone<Router>): Router =
        cicerone.router

    @Provides
    @ActivityScope
    @StartingFlow
    fun provideStartingNavigatorHolder(@StartingFlow cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder

    @Provides
    @ActivityScope
    @RegularFlow
    fun provideRegularCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    @ActivityScope
    @RegularFlow
    fun provideRegularRouter(@RegularFlow cicerone: Cicerone<Router>): Router =
        cicerone.router

    @Provides
    @ActivityScope
    @RegularFlow
    fun provideRegularNavigatorHolder(@RegularFlow cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}
