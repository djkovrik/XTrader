package com.sedsoftware.exchange.bitfinex.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.domain.repository.PairsManagerRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairsInfoRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairsManagerRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BitfinexRepositoryModule {

    @Binds
    @ForExchange(BITFINEX)
    abstract fun bindPairsInfoRepository(implementation: BitfinexPairsInfoRepository): PairsInfoRepository

    @Binds
    @ForExchange(BITFINEX)
    abstract fun bindPairsManagerRepository(implementation: BitfinexPairsManagerRepository): PairsManagerRepository
}
