package com.sedsoftware.exchange.manager.di

import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.entity.ExchangeType
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
