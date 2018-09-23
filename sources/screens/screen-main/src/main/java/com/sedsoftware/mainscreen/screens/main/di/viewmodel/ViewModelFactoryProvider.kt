package com.sedsoftware.mainscreen.screens.main.di.viewmodel

import androidx.lifecycle.ViewModelProvider

interface ViewModelFactoryProvider {

    fun provideViewModelFactory(): ViewModelProvider.Factory
}
