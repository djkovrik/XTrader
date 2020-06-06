package com.sedsoftware.exchange.manager.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.core.domain.interactor.PairTicksManager
import dagger.Module
import dagger.Provides

@Module
object ExchangeManagerModule {

    @Provides
    fun provideCurrencyPairLoaders(
        @ForExchange(BINANCE) binancePairsLoader: CurrencyPairsLoader,
        @ForExchange(BITFINEX) bitfinexPairsLoader: CurrencyPairsLoader
    ): Map<Exchange, @JvmSuppressWildcards CurrencyPairsLoader> =
        mapOf(
            BINANCE to binancePairsLoader,
            BITFINEX to bitfinexPairsLoader
        )

        @Provides
        fun provideCurrencyPairManager(
            @ForExchange(BINANCE) binancePairsManager: CurrencyPairsManager,
            @ForExchange(BITFINEX) bitfinexPairsManager: CurrencyPairsManager
        ): Map<Exchange, @JvmSuppressWildcards CurrencyPairsManager> =
            mapOf(
                BINANCE to binancePairsManager,
                BITFINEX to bitfinexPairsManager
            )

    @Provides
    fun providePairTicksManagers(
        @ForExchange(BINANCE) binanceTicksManager: PairTicksManager,
        @ForExchange(BITFINEX) bitfinexTicksManager: PairTicksManager
    ): Map<Exchange, @JvmSuppressWildcards PairTicksManager> =
        mapOf(
            BINANCE to binanceTicksManager,
            BITFINEX to bitfinexTicksManager
        )
}
