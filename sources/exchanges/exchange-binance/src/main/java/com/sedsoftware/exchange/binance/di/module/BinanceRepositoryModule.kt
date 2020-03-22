package com.sedsoftware.exchange.binance.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.domain.repository.PairsManagerRepository
import com.sedsoftware.exchange.binance.repository.BinancePairsInfoRepository
import com.sedsoftware.exchange.binance.repository.BinancePairsManagerRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BinanceRepositoryModule {

    @Binds
    @ForExchange(BINANCE)
    abstract fun bindPairsInfoRepository(implementation: BinancePairsInfoRepository): PairsInfoRepository

    @Binds
    @ForExchange(BINANCE)
    abstract fun bindPairsManagerRepository(implementation: BinancePairsManagerRepository): PairsManagerRepository
}
