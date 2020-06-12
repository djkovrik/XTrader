package com.sedsoftware.exchange.coinmarketcap.di

import com.sedsoftware.core.domain.repository.CurrencyMapRepository
import com.sedsoftware.core.domain.repository.CurrencyRepository
import com.sedsoftware.exchange.coinmarketcap.repository.CoinMarketCapCurrencyRepository
import com.sedsoftware.exchange.coinmarketcap.repository.CoinMarketCapMapRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface CoinMarketCapRepositoryModule {

    @Binds
    fun bindCurrencyRepository(implementation: CoinMarketCapCurrencyRepository): CurrencyRepository

    @Binds
    fun bindCurrencyMapRepository(implementation: CoinMarketCapMapRepository): CurrencyMapRepository
}
