package com.sedsoftware.screens.tickers.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.domain.interactor.TickerManager
import com.sedsoftware.screens.tickers.store.PairSelectionStore
import com.sedsoftware.screens.tickers.store.PairSelectionStoreFactory
import com.sedsoftware.screens.tickers.store.TickersListStore
import com.sedsoftware.screens.tickers.store.TickersListStoreFactory
import dagger.Module
import dagger.Provides

@Module
object TickersScreenModule {

    @Provides
    @ScreenScope
    fun providePairSelectionStore(
        managers: Map<Exchange, @JvmSuppressWildcards CurrencyPairManager>
    ): PairSelectionStore =
        PairSelectionStoreFactory(DefaultStoreFactory, managers).create()

    @Provides
    @ScreenScope
    fun provideTickersListStore(
        managers: Map<Exchange, @JvmSuppressWildcards TickerManager>
    ): TickersListStore =
        TickersListStoreFactory(DefaultStoreFactory, managers).create()
}
