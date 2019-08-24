package com.sedsoftware.exchange.manager.di

import com.sedsoftware.core.di.qualifier.ExchangeName
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.exchange.manager.provider.ExchangeAssetsProvider
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ExchangeManagerModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideCurrencyPairLoaders(
            @ExchangeName(BINANCE) binancePairLoader: CurrencyPairLoader
        ): Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader> =
            mapOf(BINANCE to binancePairLoader)

        @JvmStatic
        @Provides
        fun provideCurrencyPairManager(
            @ExchangeName(BINANCE) binancePairManager: CurrencyPairManager
        ): Map<Exchange, @JvmSuppressWildcards CurrencyPairManager> =
            mapOf(BINANCE to binancePairManager)
    }

    @Binds
    abstract fun bindAssetsProvider(implementation: ExchangeAssetsProvider): AssetsProvider
}
