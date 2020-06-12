package com.sedsoftware.exchange.binance.di

import com.sedsoftware.core.di.annotations.ExchangeKey
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
import dagger.multibindings.IntoMap

@Module
@InstallIn(ApplicationComponent::class)
interface BinanceExportModule {

    @Binds
    @IntoMap
    @ExchangeKey(BINANCE)
    fun bindBinancePairLoader(implementation: BinancePairsLoader): CurrencyPairsLoader

    @Binds
    @IntoMap
    @ExchangeKey(BINANCE)
    fun bindBinancePairManager(implementation: BinancePairsManager): CurrencyPairsManager

    @Binds
    @IntoMap
    @ExchangeKey(BINANCE)
    fun bindBinancePairTicksManager(implementation: BinancePairTicksManager): PairTicksManager
}
