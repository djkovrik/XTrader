package com.sedsoftware.exchangemanager.di

import com.sedsoftware.binance.entity.BinanceExchange
import com.sedsoftware.coreentity.Exchange
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
