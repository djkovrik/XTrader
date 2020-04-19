package com.sedsoftware.exchange.bitfinex.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.exchange.bitfinex.BitfinexPairsLoader
import com.sedsoftware.exchange.bitfinex.BitfinexPairsManager
import dagger.Binds
import dagger.Module

@Module
interface BitfinexExportModule {

    @Binds
    @ForExchange(BITFINEX)
    fun bindBinancePairLoader(implementation: BitfinexPairsLoader): CurrencyPairsLoader

    @Binds
    @ForExchange(BITFINEX)
    fun bindBinancePairManager(implementation: BitfinexPairsManager): CurrencyPairsManager
}
