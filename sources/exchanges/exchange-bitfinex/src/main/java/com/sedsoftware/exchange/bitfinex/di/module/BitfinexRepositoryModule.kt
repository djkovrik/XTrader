package com.sedsoftware.exchange.bitfinex.di.module

import com.sedsoftware.core.di.qualifier.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BITFINEX
import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.domain.repository.TickersRepository
import com.sedsoftware.core.domain.repository.PairsTickRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairTicksRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexPairsInfoRepository
import com.sedsoftware.exchange.bitfinex.repository.BitfinexTickersRepository
import dagger.Binds
import dagger.Module

@Module
interface BitfinexRepositoryModule {

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexPairsInfoRepository(implementation: BitfinexPairsInfoRepository): PairsInfoRepository

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexTickersRepository(implementation: BitfinexTickersRepository): TickersRepository

    @Binds
    @ForExchange(BITFINEX)
    fun bindBitfinexPairTicksRepository(implementation: BitfinexPairTicksRepository): PairsTickRepository
}
