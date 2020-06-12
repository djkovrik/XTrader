package com.sedsoftware.exchange.coinmarketcap.di

import com.sedsoftware.core.domain.interactor.CurrencyManager
import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.exchange.coinmarketcap.CoinMarketCapCurrencyManager
import com.sedsoftware.exchange.coinmarketcap.CoinMarketCapMapLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface CoinMarketCapExportModule {

    @Binds
    fun bindCurrencyProvider(implementation: CoinMarketCapCurrencyManager): CurrencyManager

    @Binds
    fun bindCurrenciesInfoLoader(implementation: CoinMarketCapMapLoader): CurrencyMapLoader
}
