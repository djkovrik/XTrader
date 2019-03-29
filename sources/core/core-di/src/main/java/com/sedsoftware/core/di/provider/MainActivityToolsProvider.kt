package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.di.coordinator.StartupCoordinator
import com.sedsoftware.core.domain.provider.AssetsProvider

interface MainActivityToolsProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideStartupCoordinator(): StartupCoordinator
    fun provideIntroCoordinator(): IntroCoordinator
    fun provideAssetsProvider(): AssetsProvider
}
