package com.sedsoftware.exchange.bitfinex.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.domain.repository.PairsManagerRepository
import com.sedsoftware.core.domain.repository.PairsTickRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairTicksRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairsInfoRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairsManagerRepository
import dagger.Binds
import dagger.Module

@Module
interface BitfinexRepositoryModule {

    @Binds
    @ForExchange(BITFINEX)
    fun bindPairsInfoRepository(implementation: BitfinexPairsInfoRepository): PairsInfoRepository

    @Binds
    @ForExchange(BITFINEX)
    fun bindPairsManagerRepository(implementation: BitfinexPairsManagerRepository): PairsManagerRepository

    @Binds
    @ForExchange(BITFINEX)
    fun bindPairsTickRepository(implementation: BitfinexPairTicksRepository): PairsTickRepository
}
