package com.sedsoftware.main.flows.starting.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.key.ViewModelKey
import com.sedsoftware.main.factory.ViewModelOwnerFactory
import com.sedsoftware.screens.intro.IntroScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StartingFlowModule {

    @Binds
    abstract fun bindViewModelOwnerFactory(factory: ViewModelOwnerFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(IntroScreenViewModel::class)
    abstract fun bindIntroViewModel(introScreenViewModel: IntroScreenViewModel): ViewModel
}
