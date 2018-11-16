package com.sedsoftware.screens.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.key.ViewModelKey
import com.sedsoftware.screens.main.MainViewModel
import com.sedsoftware.screens.main.factory.ViewModelOwnerFactory
import com.sedsoftware.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelOwnerFactory(factory: ViewModelOwnerFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}
