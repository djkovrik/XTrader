package com.sedsoftware.exchange.binance.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.domain.interactor.TickerManager
import com.sedsoftware.exchange.binance.BinanceCurrencyPairManager
import com.sedsoftware.exchange.binance.BinancePairLoader
import com.sedsoftware.exchange.binance.BinanceTickerManager
import dagger.Binds
import dagger.Module

@Module
interface BinanceExportModule {

    @Binds
    @ForExchange(BINANCE)
    fun bindBinanceCurrencyPairLoader(implementation: BinancePairLoader): CurrencyPairLoader

    @Binds
    @ForExchange(BINANCE)
    fun bindBinanceCurrencyPairManager(implementation: BinanceCurrencyPairManager): CurrencyPairManager

    @Binds
    @ForExchange(BINANCE)
    fun bindBinanceTickerManager(implementation: BinanceTickerManager): TickerManager
}
