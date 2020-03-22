package com.sedsoftware.exchange.binance.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.exchange.binance.BinancePairLoader
import com.sedsoftware.exchange.binance.BinancePairManager
import dagger.Binds
import dagger.Module

@Module
interface BinanceExportModule {

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairLoader(implementation: BinancePairLoader): CurrencyPairLoader

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairManager(implementation: BinancePairManager): CurrencyPairManager
}
