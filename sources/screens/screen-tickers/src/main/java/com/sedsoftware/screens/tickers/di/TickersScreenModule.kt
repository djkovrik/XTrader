package com.sedsoftware.screens.tickers.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.TickersManager
import com.sedsoftware.core.domain.interactor.PairTicksManager
import com.sedsoftware.screens.tickers.store.TickersListStore
import com.sedsoftware.screens.tickers.store.TickersListStoreFactory
import com.sedsoftware.screens.tickers.store.PairSelectionStore
import com.sedsoftware.screens.tickers.store.PairSelectionStoreFactory
import dagger.Module
import dagger.Provides

@Module
object TickersScreenModule {

    @Provides
    @ScreenScope
    fun providePairSelectionStore(
        managers: Map<Exchange, @JvmSuppressWildcards TickersManager>
    ): PairSelectionStore =
        PairSelectionStoreFactory(DefaultStoreFactory, managers).create()

    @Provides
    @ScreenScope
    fun provideTickersListStore(
        managers: Map<Exchange, @JvmSuppressWildcards PairTicksManager>
    ): TickersListStore =
        TickersListStoreFactory(DefaultStoreFactory, managers).create()
}
