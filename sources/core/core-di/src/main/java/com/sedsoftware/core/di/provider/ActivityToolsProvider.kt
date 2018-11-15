package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.device.api.Logger
import com.sedsoftware.core.di.holder.ActivityComponentHolder
import com.sedsoftware.core.di.holder.NavControllerHolder

interface ActivityToolsProvider {
    fun provideLogger(): Logger
    fun provideNavControllerHolder(): NavControllerHolder
    fun provideViewModelFactory(): ViewModelProvider.Factory

    fun inject(activity: ActivityComponentHolder)
}
