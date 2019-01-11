package com.sedsoftware.core.di.provider

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.navigation.destination.DestinationFactory
import com.sedsoftware.core.tools.api.Settings
import javax.inject.Provider

interface MainActivityToolsProvider {
    fun provideDestinationFactories(): Map<Class<out Fragment>, @JvmSuppressWildcards Provider<DestinationFactory>>
    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideSettings(): Settings
    fun provideRouter(): Router
}
