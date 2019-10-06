package com.sedsoftware.screens.main.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.key.ViewModelKey
import com.sedsoftware.screens.intro.IntroScreenViewModel
import com.sedsoftware.screens.main.factory.ViewModelOwnerFactory
import com.sedsoftware.screens.market.MarketScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FlowViewModelsModule {

    @Binds
    abstract fun bindViewModelOwnerFactory(factory: ViewModelOwnerFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(IntroScreenViewModel::class)
    abstract fun bindIntroViewModel(introScreenViewModel: IntroScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarketScreenViewModel::class)
    abstract fun bindMarketScreenViewModel(marketScreenViewModel: MarketScreenViewModel): ViewModel
}
