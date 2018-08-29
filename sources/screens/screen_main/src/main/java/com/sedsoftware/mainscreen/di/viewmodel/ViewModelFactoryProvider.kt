package com.sedsoftware.mainscreen.di.viewmodel

import androidx.lifecycle.ViewModelProvider

interface ViewModelFactoryProvider {

    fun provideViewModelFactory(): ViewModelProvider.Factory
}
