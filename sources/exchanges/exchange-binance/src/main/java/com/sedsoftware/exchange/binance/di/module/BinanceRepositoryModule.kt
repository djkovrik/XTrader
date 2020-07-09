package com.sedsoftware.exchange.binance.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.domain.repository.TickersRepository
import com.sedsoftware.core.domain.repository.PairsTickRepository
import com.sedsoftware.exchange.binance.repository.BinancePairsInfoRepository
import com.sedsoftware.exchange.binance.repository.BinanceTickersRepository
import com.sedsoftware.exchange.binance.repository.BinancePairsTickRepository
import dagger.Binds
import dagger.Module

@Module
interface BinanceRepositoryModule {

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairsInfoRepository(implementation: BinancePairsInfoRepository): PairsInfoRepository

    @Binds
    @ForExchange(BINANCE)
    fun bindBinanceTickersRepository(implementation: BinanceTickersRepository): TickersRepository

    @Binds
    @ForExchange(BINANCE)
    fun bindBinancePairsTickRepository(implementation: BinancePairsTickRepository): PairsTickRepository
}
