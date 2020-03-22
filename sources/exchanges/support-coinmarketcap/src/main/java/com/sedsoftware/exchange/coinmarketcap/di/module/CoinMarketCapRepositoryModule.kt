package com.sedsoftware.exchange.coinmarketcap.di.module

import com.sedsoftware.core.domain.repository.CurrencyMapRepository
import com.sedsoftware.exchange.coinmarketcap.repository.CoinMarketCapCurrencyMapRepository
import dagger.Binds
import dagger.Module

@Module
abstract class CoinMarketCapRepositoryModule {

    @Binds
    abstract fun bindCurrencyMapRepository(implementation: CoinMarketCapCurrencyMapRepository): CurrencyMapRepository
}
