package com.sedsoftware.exchange.coinmarketcap.di.module

import com.sedsoftware.core.domain.interactor.CurrenciesInfoLoader
import com.sedsoftware.core.domain.provider.CurrencyProvider
import com.sedsoftware.exchange.coinmarketcap.CoinMarketCapCurrencyProvider
import com.sedsoftware.exchange.coinmarketcap.CoinMarketCapInfoLoader
import dagger.Binds
import dagger.Module

@Module
interface CoinMarketCapModule {

    @Binds
    abstract fun bindCurrencyProvider(implementation: CoinMarketCapCurrencyProvider): CurrencyProvider

    @Binds
    abstract fun bindCurrenciesInfoLoader(implementation: CoinMarketCapInfoLoader): CurrenciesInfoLoader
}
