package com.sedsoftware.main.flows.starting.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.key.ViewModelKey
import com.sedsoftware.core.di.qualifier.StartingFlow
import com.sedsoftware.main.flows.starting.factory.StartingViewModelOwnerFactory
import com.sedsoftware.screens.intro.exchanges.IntroExchangesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StartingFlowViewModelsModule {

    @Binds
    @StartingFlow
    abstract fun bindViewModelOwnerFactory(factory: StartingViewModelOwnerFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @StartingFlow
    @ViewModelKey(IntroExchangesViewModel::class)
    abstract fun bindIntroScreenViewModel(introScreenViewModel: IntroExchangesViewModel): ViewModel
}
