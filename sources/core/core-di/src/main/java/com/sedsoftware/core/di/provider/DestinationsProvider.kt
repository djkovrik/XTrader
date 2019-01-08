package com.sedsoftware.core.di.provider

import androidx.fragment.app.Fragment
import com.sedsoftware.core.navigation.destination.DestinationFactory
import javax.inject.Provider

interface DestinationsProvider {
    fun provideDestinationFactories(): Map<Class<out Fragment>, @JvmSuppressWildcards Provider<DestinationFactory>>
}
