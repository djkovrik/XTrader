package com.sedsoftware.exchange.manager.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
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

    companion object {

        @Provides
        fun provideCurrencyPairLoaders(
            @ForExchange(BINANCE) binancePairLoader: CurrencyPairLoader,
            @ForExchange(BITFINEX) bitfinexPairLoader: CurrencyPairLoader
        ): Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader> =
            mapOf(
                BINANCE to binancePairLoader,
                BITFINEX to bitfinexPairLoader
            )

        @Provides
        fun provideCurrencyPairManager(
            @ForExchange(BINANCE) binancePairManager: CurrencyPairManager,
            @ForExchange(BITFINEX) bitfinexPairManager: CurrencyPairManager
        ): Map<Exchange, @JvmSuppressWildcards CurrencyPairManager> =
            mapOf(
                BINANCE to binancePairManager,
                BITFINEX to bitfinexPairManager
            )
    }

    @Binds
    abstract fun bindAssetsProvider(implementation: ExchangeAssetsProvider): AssetsProvider
}
