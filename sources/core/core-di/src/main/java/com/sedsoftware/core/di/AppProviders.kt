package com.sedsoftware.core.di

import android.view.Display
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.di.qualifier.StartingFlow
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.coordinator.FlowCoordinator
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

// App
interface AppProvider :
    DeviceToolsProvider,
    ExchangeManagerProvider

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

interface ExchangeManagerProvider {
    fun provideExchangePairLoaders(): Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader>
    fun provideExchangePairManagers(): Map<Exchange, @JvmSuppressWildcards CurrencyPairManager>
    fun provideIconsProvider(): AssetsProvider
}

interface BinanceProvider {
    @ForExchange(BINANCE) fun provideBinancePairLoader(): CurrencyPairLoader
    @ForExchange(BINANCE) fun provideBinancePairManager(): CurrencyPairManager
}

interface CoinMarketCapProvider {
    fun provideCurrencyProvider(): CurrencyProvider
    fun provideCurrenciesInfoLoader(): CurrenciesInfoLoader
}

// Local providers
interface ActivityToolsProvider {
    fun provideFlowCoordinator(): FlowCoordinator
}

interface StartingFlowToolsProvider : ActivityToolsProvider {
    @StartingFlow fun provideViewModelFactory(): ViewModelProvider.Factory
}

interface RegularFlowToolsProvider : ActivityToolsProvider {

}
