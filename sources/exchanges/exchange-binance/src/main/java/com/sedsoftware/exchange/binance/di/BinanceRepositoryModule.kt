package com.sedsoftware.exchange.binance.di

import com.sedsoftware.core.di.annotations.ForExchange
import com.sedsoftware.core.domain.ExchangeType.BINANCE
import com.sedsoftware.core.domain.repository.PairsInfoRepository
import com.sedsoftware.core.domain.repository.PairsManagerRepository
import com.sedsoftware.core.domain.repository.PairsTickRepository
import com.sedsoftware.exchange.binance.repository.BinancePairsInfoRepository
import com.sedsoftware.exchange.binance.repository.BinancePairsManagerRepository
import com.sedsoftware.exchange.binance.repository.BinancePairsTickRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface BinanceRepositoryModule {

    @Binds
    @ForExchange(BINANCE)
    fun bindPairsInfoRepository(implementation: BinancePairsInfoRepository): PairsInfoRepository

    @Binds
    @ForExchange(BINANCE)
    fun bindPairsManagerRepository(implementation: BinancePairsManagerRepository): PairsManagerRepository

    @Binds
    @ForExchange(BINANCE)
    fun bindPairsTickRepository(implementation: BinancePairsTickRepository): PairsTickRepository
}
