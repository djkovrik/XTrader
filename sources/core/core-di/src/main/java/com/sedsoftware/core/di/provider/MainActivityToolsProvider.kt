package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.coordinator.SplashCoordinator

interface MainActivityToolsProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideSplashCoordinator(): SplashCoordinator
}
