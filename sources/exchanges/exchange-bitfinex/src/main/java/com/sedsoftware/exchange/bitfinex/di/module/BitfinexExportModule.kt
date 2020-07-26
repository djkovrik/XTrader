package com.sedsoftware.exchange.bitfinex.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.domain.interactor.TickerManager
import com.sedsoftware.exchange.bitfinex.BitfinexCurrencyPairManager
import com.sedsoftware.exchange.bitfinex.BitfinexPairLoader
import com.sedsoftware.exchange.bitfinex.BitfinexTickerManager
import dagger.Binds
import dagger.Module

@Module
interface BitfinexExportModule {

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexCurrencyPairLoader(implementation: BitfinexPairLoader): CurrencyPairLoader

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexCurrencyPairManager(implementation: BitfinexCurrencyPairManager): CurrencyPairManager

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexTickerManager(implementation: BitfinexTickerManager): TickerManager
}
