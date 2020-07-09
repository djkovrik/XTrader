package com.sedsoftware.exchange.bitfinex.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.interactor.TickersManager
import com.sedsoftware.core.domain.interactor.PairTicksManager
import com.sedsoftware.exchange.bitfinex.BitfinexPairTicksManager
import com.sedsoftware.exchange.bitfinex.BitfinexPairsLoader
import com.sedsoftware.exchange.bitfinex.BitfinexTickersManager
import dagger.Binds
import dagger.Module

@Module
interface BitfinexExportModule {

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexPairsLoader(implementation: BitfinexPairsLoader): CurrencyPairsLoader

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexTickersManager(implementation: BitfinexTickersManager): TickersManager

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexPairTicksManager(implementation: BitfinexPairTicksManager): PairTicksManager
}
