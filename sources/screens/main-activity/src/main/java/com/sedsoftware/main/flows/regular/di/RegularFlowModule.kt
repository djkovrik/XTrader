package com.sedsoftware.main.flows.regular.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.key.ViewModelKey
import com.sedsoftware.core.di.scope.FlowScope
import com.sedsoftware.main.factory.ViewModelOwnerFactory
import com.sedsoftware.screens.market.MarketScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
abstract class RegularFlowModule {

    @Module
    companion object {

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

    @Binds
    abstract fun bindViewModelOwnerFactory(factory: ViewModelOwnerFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MarketScreenViewModel::class)
    abstract fun bindMarketScreenViewModel(marketScreenViewModel: MarketScreenViewModel): ViewModel
}
