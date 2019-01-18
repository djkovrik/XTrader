package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider

interface MainActivityToolsProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
}
