package com.sedsoftware.exchange.bitfinex.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.repository.PairInfoRepository
import com.sedsoftware.core.domain.repository.PairManagerRepository
import com.sedsoftware.core.domain.repository.TickerRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairInfoRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairManagerRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexTickerRepository
import dagger.Binds
import dagger.Module

@Module
interface BitfinexRepositoryModule {

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexPairInfoRepository(implementation: BitfinexPairInfoRepository): PairInfoRepository

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexTickersRepository(implementation: BitfinexPairManagerRepository): PairManagerRepository

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexPairTicksRepository(implementation: BitfinexTickerRepository): TickerRepository
}
