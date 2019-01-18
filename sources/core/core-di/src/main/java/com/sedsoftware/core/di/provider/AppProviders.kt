package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.App
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.tools.api.Executor
import com.sedsoftware.core.tools.api.Logger
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.core.tools.api.Signer

interface AppProvider :
    DeviceToolsProvider,
    ViewModelFactoryProvider

interface DeviceToolsProvider {
    fun provideApp(): App
    fun provideExecutor(): Executor
    fun provideLogger(): Logger
    fun provideSettings(): Settings
    fun provideSigner(): Signer
}

interface ViewModelFactoryProvider : ExchangeManagerProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
}

interface ExchangeManagerProvider : BinanceProvider {
    fun provideSupportedExchanges(): Set<@JvmSuppressWildcards Exchange>
}

interface BinanceProvider
