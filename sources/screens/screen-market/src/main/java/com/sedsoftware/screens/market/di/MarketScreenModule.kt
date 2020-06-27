package com.sedsoftware.screens.market.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.core.domain.interactor.PairTicksManager
import com.sedsoftware.screens.market.store.MarketListStore
import com.sedsoftware.screens.market.store.MarketListStoreFactory
import com.sedsoftware.screens.market.store.PairSelectionStore
import com.sedsoftware.screens.market.store.PairSelectionStoreFactory
import dagger.Module
import dagger.Provides

@Module
object MarketScreenModule {

    @Provides
    @ScreenScope
    fun providePairSelectionStore(
        managers: Map<Exchange, @JvmSuppressWildcards CurrencyPairsManager>
    ): PairSelectionStore =
        PairSelectionStoreFactory(DefaultStoreFactory, managers).create()

    @Provides
    @ScreenScope
    fun provideMarketListStore(
        managers: Map<Exchange, @JvmSuppressWildcards PairTicksManager>
    ): MarketListStore =
        MarketListStoreFactory(DefaultStoreFactory, managers).create()
}
