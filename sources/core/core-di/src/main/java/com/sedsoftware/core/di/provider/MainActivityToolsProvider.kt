package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.tools.api.Settings

interface MainActivityToolsProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideSettings(): Settings
}
