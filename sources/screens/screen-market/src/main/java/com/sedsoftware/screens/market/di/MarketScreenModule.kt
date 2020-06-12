package com.sedsoftware.screens.market.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.screens.market.store.MarketStore
import com.sedsoftware.screens.market.store.MarketStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object MarketScreenModule {

    @Provides
    @FragmentScoped
    fun provideMarketStore(
        managers: Map<Exchange, @JvmSuppressWildcards CurrencyPairsManager>
    ): MarketStore =
        MarketStoreFactory(DefaultStoreFactory, managers).create()
}
