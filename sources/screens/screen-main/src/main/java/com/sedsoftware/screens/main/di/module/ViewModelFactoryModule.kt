package com.sedsoftware.screens.main.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.key.ViewModelKey
import com.sedsoftware.screens.intro.IntroScreenViewModel
import com.sedsoftware.screens.main.MainActivityViewModel
import com.sedsoftware.screens.main.factory.ViewModelOwnerFactory
import com.sedsoftware.screens.market.MarketScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelOwnerFactory(factory: ViewModelOwnerFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IntroScreenViewModel::class)
    abstract fun bindIntroViewModel(introScreenViewModel: IntroScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarketScreenViewModel::class)
    abstract fun bindMarketScreenViewModell(marketScreenViewModel: MarketScreenViewModel): ViewModel
}
