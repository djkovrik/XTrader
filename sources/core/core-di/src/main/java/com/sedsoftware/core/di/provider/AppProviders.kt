package com.sedsoftware.core.di.provider

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.App
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.navigation.destination.DestinationFactory
import com.sedsoftware.core.tools.api.Executor
import com.sedsoftware.core.tools.api.Logger
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.core.tools.api.Signer
import javax.inject.Provider

interface AppProvider :
    DeviceToolsProvider,
    DestinationFactoryProvider,
    NavigationProvider,
    ViewModelFactoryProvider

interface DeviceToolsProvider {
    fun provideApp(): App
    fun provideExecutor(): Executor
    fun provideLogger(): Logger
    fun provideSettings(): Settings
    fun provideSigner(): Signer
}

interface DestinationFactoryProvider {
    fun provideDestinationFactories(): Map<Class<out Fragment>, @JvmSuppressWildcards Provider<DestinationFactory>>
}

interface NavigationProvider {
    fun provideNavControllerHolder(): NavControllerHolder
    fun provideRouter(): Router
}

interface ViewModelFactoryProvider : ExchangeManagerProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
}

interface ExchangeManagerProvider : BinanceProvider {
    fun provideSupportedExchanges(): Set<@JvmSuppressWildcards Exchange>
}

interface BinanceProvider
