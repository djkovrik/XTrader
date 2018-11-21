package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.holder.ActivityComponentHolder

interface MainActivityToolsProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory

    fun inject(activity: ActivityComponentHolder)
}
