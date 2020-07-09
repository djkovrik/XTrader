package com.sedsoftware.exchange.binance.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.interactor.TickersManager
import com.sedsoftware.core.domain.interactor.PairTicksManager
import com.sedsoftware.exchange.binance.BinancePairTicksManager
import com.sedsoftware.exchange.binance.BinancePairsLoader
import com.sedsoftware.exchange.binance.BinanceTickersManager
import dagger.Binds
import dagger.Module

@Module
interface BinanceExportModule {

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairsLoader(implementation: BinancePairsLoader): CurrencyPairsLoader

    @Binds
    @ForExchange(BINANCE)
    fun bindBinanceTickersManager(implementation: BinanceTickersManager): TickersManager

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairTicksManager(implementation: BinancePairTicksManager): PairTicksManager
}
