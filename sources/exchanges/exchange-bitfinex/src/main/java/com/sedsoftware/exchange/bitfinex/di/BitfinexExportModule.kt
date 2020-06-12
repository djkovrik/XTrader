package com.sedsoftware.exchange.bitfinex.di

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.core.domain.interactor.PairTicksManager
import com.sedsoftware.exchange.bitfinex.BitfinexPairTicksManager
import com.sedsoftware.exchange.bitfinex.BitfinexPairsLoader
import com.sedsoftware.exchange.bitfinex.BitfinexPairsManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface BitfinexExportModule {

    @Binds
    @ForExchange(BITFINEX)
    fun bindBinancePairLoader(implementation: BitfinexPairsLoader): CurrencyPairsLoader

    @Binds
    @ForExchange(BITFINEX)
    fun bindBinancePairManager(implementation: BitfinexPairsManager): CurrencyPairsManager

    @Binds
    @ForExchange(BITFINEX)
    fun bindPairTicksManager(implementation: BitfinexPairTicksManager): PairTicksManager
}
