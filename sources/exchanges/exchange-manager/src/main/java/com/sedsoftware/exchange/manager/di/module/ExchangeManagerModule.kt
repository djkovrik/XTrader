package com.sedsoftware.exchange.manager.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.domain.interactor.TickerManager
import dagger.Module
import dagger.Provides

@Module
object ExchangeManagerModule {

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

    @Provides
    fun provideTickerManagers(
        @ForExchange(BINANCE) binanceTickerManager: TickerManager,
        @ForExchange(BITFINEX) bitfinexTickerManager: TickerManager
    ): Map<Exchange, @JvmSuppressWildcards TickerManager> =
        mapOf(
            BINANCE to binanceTickerManager,
            BITFINEX to bitfinexTickerManager
        )
}
