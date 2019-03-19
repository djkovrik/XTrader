package com.sedsoftware.exchange.manager.di

import com.sedsoftware.core.di.qualifier.ExchangeName
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import dagger.Module
import dagger.Provides

@Module
class ExchangeManagerModule {

    @Provides
    fun provideCurrencyPairLoaders(
        @ExchangeName(BINANCE) binancePairsLoader: CurrencyPairLoader
    ): Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader> =
        mapOf(BINANCE to binancePairsLoader)
}