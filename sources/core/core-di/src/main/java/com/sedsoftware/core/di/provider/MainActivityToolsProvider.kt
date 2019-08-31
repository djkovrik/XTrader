package com.sedsoftware.core.di.provider

import android.view.Display
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.di.delegate.NavigationFlowDelegate
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.domain.provider.AssetsProvider

interface MainActivityToolsProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideIntroCoordinator(): IntroCoordinator
    fun provideSnackbarDelegate(): SnackbarDelegate
    fun provideNavigationFlowDelegate(): NavigationFlowDelegate
    fun provideAssetsProvider(): AssetsProvider
    fun provideDisplay(): Display
}
