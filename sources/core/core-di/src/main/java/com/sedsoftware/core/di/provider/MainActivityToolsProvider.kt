package com.sedsoftware.core.di.provider

import android.view.Display
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.domain.provider.AssetsProvider

interface MainActivityToolsProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideSnackbarDelegate(): SnackbarDelegate
    fun provideAssetsProvider(): AssetsProvider
    fun provideDisplay(): Display
}
