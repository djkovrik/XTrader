package com.sedsoftware.exchangemanager.di

import com.sedsoftware.coreentity.ExchangeType
import com.sedsoftware.coreentity.Exchange
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class ExchangeManagerModule {

    @Provides
    @IntoSet
    fun provideBinanceExchange(): Exchange =
        ExchangeType.BINANCE
}
