package com.sedsoftware.exchange.coinmarketcap.di.module

import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.core.domain.provider.CurrencyProvider
import com.sedsoftware.exchange.coinmarketcap.CoinMarketCapCurrencyProvider
import com.sedsoftware.exchange.coinmarketcap.CoinMarketCapMapLoader
import dagger.Binds
import dagger.Module

@Module
interface CoinMarketCapModule {

    @Binds
    abstract fun bindCurrencyProvider(implementation: CoinMarketCapCurrencyProvider): CurrencyProvider

    @Binds
    abstract fun bindCurrenciesInfoLoader(implementation: CoinMarketCapMapLoader): CurrencyMapLoader
}
