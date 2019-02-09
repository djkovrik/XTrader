package com.sedsoftware.screens.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.key.ViewModelKey
import com.sedsoftware.screens.intro.viewmodel.IntroDownloadsViewModel
import com.sedsoftware.screens.main.factory.ViewModelOwnerFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelOwnerFactory(factory: ViewModelOwnerFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(IntroDownloadsViewModel::class)
    abstract fun bindIntroViewModel(introScreenViewModel: IntroDownloadsViewModel): ViewModel
}
