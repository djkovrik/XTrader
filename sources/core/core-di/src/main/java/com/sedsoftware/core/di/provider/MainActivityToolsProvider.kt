package com.sedsoftware.core.di.provider

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.holder.ActivityComponentHolder
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import com.sedsoftware.core.tools.api.Settings
import javax.inject.Provider

interface MainActivityToolsProvider {
    fun provideDirectionsFactoryMap(): Map<Class<out Fragment>, @JvmSuppressWildcards Provider<NavDirectionsFactory>>
    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideSettings(): Settings

    fun inject(activity: ActivityComponentHolder)
}
