package com.sedsoftware.exchange.binance.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.repository.PairManagerRepository
import com.sedsoftware.core.domain.repository.PairInfoRepository
import com.sedsoftware.core.domain.repository.TickerRepository
import com.sedsoftware.exchange.binance.repository.BinancePairManagerRepository
import com.sedsoftware.exchange.binance.repository.BinancePairInfoRepository
import com.sedsoftware.exchange.binance.repository.BinanceTickerRepository
import dagger.Binds
import dagger.Module

@Module
interface BinanceRepositoryModule {

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairInfoRepository(implementation: BinancePairInfoRepository): PairInfoRepository

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairManagerRepository(implementation: BinancePairManagerRepository): PairManagerRepository

    @Binds
    @ForExchange(BINANCE)
    fun bindBinanceTickerRepository(implementation: BinanceTickerRepository): TickerRepository
}
