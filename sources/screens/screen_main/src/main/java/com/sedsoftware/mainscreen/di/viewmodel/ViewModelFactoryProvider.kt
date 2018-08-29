package com.sedsoftware.mainscreen.di.viewmodel

import com.sedsoftware.core.factory.ViewModelFactory

interface ViewModelFactoryProvider {

    fun provideViewModelFactory(): ViewModelFactory
}
