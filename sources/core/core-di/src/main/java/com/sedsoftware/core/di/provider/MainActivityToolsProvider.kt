package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.holder.ActivityComponentHolder
import com.sedsoftware.core.di.holder.NavControllerHolder
import com.sedsoftware.core.domain.navigation.SplashCoordinator

interface MainActivityToolsProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideNavControllerHolder(): NavControllerHolder
    fun provideSplasScreenCoordinator(): SplashCoordinator

    fun inject(activity: ActivityComponentHolder)
}
