package com.sedsoftware.mainscreen.di.viewmodel

import com.sedsoftware.core.di.key.ViewModelOwnerKey
import com.sedsoftware.core.factory.ViewModelFactory
import com.sedsoftware.core.marker.ViewModelOwner
import com.sedsoftware.mainscreen.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelOwnerFactory(factory: ViewModelOwnerFactory): ViewModelFactory

    @Binds
    @IntoMap
    @ViewModelOwnerKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModelOwner
}
