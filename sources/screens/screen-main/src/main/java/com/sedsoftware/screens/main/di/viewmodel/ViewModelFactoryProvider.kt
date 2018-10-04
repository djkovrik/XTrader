package com.sedsoftware.screens.main.di.viewmodel

import androidx.lifecycle.ViewModelProvider

interface ViewModelFactoryProvider {

    fun provideViewModelFactory(): ViewModelProvider.Factory
}
