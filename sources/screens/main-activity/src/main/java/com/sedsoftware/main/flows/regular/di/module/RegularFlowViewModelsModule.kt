package com.sedsoftware.main.flows.regular.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.key.ViewModelKey
import com.sedsoftware.core.di.qualifier.RegularFlow
import com.sedsoftware.main.flows.regular.factory.RegularViewModelOwnerFactory
import com.sedsoftware.screens.intro.IntroScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RegularFlowViewModelsModule {

    @Binds
    @RegularFlow
    abstract fun bindViewModelOwnerFactory(factory: RegularViewModelOwnerFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @RegularFlow
    @ViewModelKey(IntroScreenViewModel::class)
    abstract fun bindIntroScreenViewModel(introScreenViewModel: IntroScreenViewModel): ViewModel
}
