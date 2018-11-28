package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.device.api.Settings

interface ViewModelFactoryProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideSettings(): Settings
}
