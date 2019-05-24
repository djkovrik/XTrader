package com.sedsoftware.core.di.provider

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.qualifier.ExchangeName
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.tools.api.Logger
import com.sedsoftware.core.tools.api.NetworkHandler
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.core.tools.api.Signer
import okhttp3.OkHttpClient

interface AppProvider :
    DeviceToolsProvider,
    NavigationProvider,
    ViewModelFactoryProvider

interface DeviceToolsProvider {
    fun provideApp(): App
    fun provideLogger(): Logger
    fun provideNetworkHandler(): NetworkHandler
    fun provideSettings(): Settings
    fun provideSigner(): Signer
    fun provideOkHttpClient(): OkHttpClient
}

interface NavigationProvider {
    fun provideNavControllerHolder(): NavControllerHolder
    fun provideRouter(): Router
}

interface ViewModelFactoryProvider : ExchangeManagerProvider {
    fun provideViewModelFactory(): ViewModelProvider.Factory
}

interface ExchangeManagerProvider : BinanceProvider {
    fun provideExchangePairLoaders(): Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader>
    fun provideIconsProvider(): AssetsProvider
}

interface BinanceProvider {

    @ExchangeName(BINANCE)
    fun provideBinancePairLoader(): CurrencyPairLoader
}
