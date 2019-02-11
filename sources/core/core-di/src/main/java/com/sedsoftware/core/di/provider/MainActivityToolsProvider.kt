package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.di.coordinator.StartupCoordinator

interface MainActivityToolsProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideStartupCoordinator(): StartupCoordinator
    fun provideIntroCoordinator(): IntroCoordinator
}
