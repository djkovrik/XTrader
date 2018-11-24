package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider

interface ViewModelFactoryProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
}
