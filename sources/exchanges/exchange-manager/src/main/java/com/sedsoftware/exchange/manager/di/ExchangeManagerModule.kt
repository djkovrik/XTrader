package com.sedsoftware.exchange.manager.di

import com.sedsoftware.core.di.qualifier.Exchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet

@Module
class ExchangeManagerModule {

    @Provides
    @ElementsIntoSet
    fun provideCurrencyPairLoaders(
        @Exchange(BINANCE) binance: CurrencyPairLoader
    ): Set<CurrencyPairLoader> =
        setOf(
            binance
        )
}