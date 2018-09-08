package com.sedsoftware.exchangemanager.di

import com.sedsoftware.binance.entity.BinanceExchange
import com.sedsoftware.core.entity.Exchange
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class ExchangeManagerModule {

    @Provides
    @IntoSet
    fun provideBinanceExchange(): Exchange =
        BinanceExchange.BINANCE
}
