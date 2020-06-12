package com.sedsoftware.exchange.binance.di

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.core.domain.interactor.PairTicksManager
import com.sedsoftware.exchange.binance.BinancePairTicksManager
import com.sedsoftware.exchange.binance.BinancePairsLoader
import com.sedsoftware.exchange.binance.BinancePairsManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface BinanceExportModule {

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairLoader(implementation: BinancePairsLoader): CurrencyPairsLoader

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairManager(implementation: BinancePairsManager): CurrencyPairsManager

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairTicksManager(implementation: BinancePairTicksManager): PairTicksManager
}
