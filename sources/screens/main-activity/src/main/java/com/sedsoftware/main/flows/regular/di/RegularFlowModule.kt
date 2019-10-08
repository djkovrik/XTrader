package com.sedsoftware.main.flows.regular.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.key.ViewModelKey
import com.sedsoftware.main.factory.ViewModelOwnerFactory
import com.sedsoftware.screens.market.MarketScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RegularFlowModule {

    @Binds
    abstract fun bindViewModelOwnerFactory(factory: ViewModelOwnerFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MarketScreenViewModel::class)
    abstract fun bindMarketScreenViewModel(marketScreenViewModel: MarketScreenViewModel): ViewModel
}
