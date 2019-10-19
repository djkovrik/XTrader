package com.sedsoftware.main.flows.starting.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.key.ViewModelKey
import com.sedsoftware.core.di.qualifier.StartingFlow
import com.sedsoftware.main.factory.ViewModelOwnerFactory
import com.sedsoftware.screens.intro.IntroScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StartingFlowViewModelsModule {

    @Binds
    @StartingFlow
    abstract fun bindViewModelOwnerFactory(factory: ViewModelOwnerFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @StartingFlow
    @ViewModelKey(IntroScreenViewModel::class)
    abstract fun bindIntroScreenViewModel(marketScreenViewModel: IntroScreenViewModel): ViewModel
}
