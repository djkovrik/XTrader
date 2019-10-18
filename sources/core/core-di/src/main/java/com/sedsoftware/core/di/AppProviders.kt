package com.sedsoftware.core.di

import android.view.Display
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.di.qualifier.Global
import com.sedsoftware.core.di.qualifier.RegularFlow
import com.sedsoftware.core.di.qualifier.StartingFlow
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrenciesInfoLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.domain.provider.CurrencyProvider
import com.sedsoftware.core.tools.api.Logger
import com.sedsoftware.core.tools.api.NetworkHandler
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.core.tools.api.Signer
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

// App
interface AppProvider :
    DeviceToolsProvider,
    ExchangeManagerProvider,
    NavigationToolsProvider

// Global providers
interface DeviceToolsProvider {
    fun provideApp(): App
    fun provideLogger(): Logger
    fun provideNetworkHandler(): NetworkHandler
    fun provideSettings(): Settings
    fun provideSigner(): Signer
    fun provideMoshi(): Moshi
    fun provideOkHttpClient(): OkHttpClient
    fun provideDefaultDisplay(): Display
}

interface NavigationToolsProvider {
    @Global
    fun provideCicerone(): Cicerone<Router>

    @Global
    fun provideRouter(): Router

    @Global
    fun provideNavigatorHolder(): NavigatorHolder
}

interface ExchangeManagerProvider {
    fun provideExchangePairLoaders(): Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader>
    fun provideExchangePairManagers(): Map<Exchange, @JvmSuppressWildcards CurrencyPairManager>
    fun provideIconsProvider(): AssetsProvider
}

interface BinanceProvider {
    @ForExchange(BINANCE)
    fun provideBinancePairLoader(): CurrencyPairLoader

    @ForExchange(BINANCE)
    fun provideBinancePairManager(): CurrencyPairManager
}

interface CoinMarketCapProvider {
    fun provideCurrencyProvider(): CurrencyProvider
    fun provideCurrenciesInfoLoader(): CurrenciesInfoLoader
}

// Local providers
interface ActivityToolsProvider {
    fun provideSnackBarDelegate(): SnackbarDelegate
}

interface StartingFlowToolsProvider {
    @StartingFlow
    fun provideCicerone(): Cicerone<Router>

    @StartingFlow
    fun provideRouter(): Router

    @StartingFlow
    fun provideNavigatorHolder(): NavigatorHolder
}

interface RegularFlowToolsProvider {
    @RegularFlow
    fun provideCicerone(): Cicerone<Router>

    @RegularFlow
    fun provideRouter(): Router

    @RegularFlow
    fun provideNavigatorHolder(): NavigatorHolder
}
