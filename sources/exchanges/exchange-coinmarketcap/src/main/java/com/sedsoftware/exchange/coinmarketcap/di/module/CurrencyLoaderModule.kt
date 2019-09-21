package com.sedsoftware.exchange.coinmarketcap.di.module

import com.sedsoftware.core.domain.interactor.CurrenciesInfoLoader
import com.sedsoftware.exchange.coinmarketcap.CoinMarketCapInfoLoader
import dagger.Binds
import dagger.Module

@Module
interface CurrencyLoaderModule {

    @Binds
    abstract fun bindCurrenciesInfoLoader(implementation: CoinMarketCapInfoLoader): CurrenciesInfoLoader
}
