package com.sedsoftware.screens.market.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.core.presentation.event.OneTimeEvent
import com.sedsoftware.screens.market.store.MarketStore
import com.sedsoftware.screens.market.store.MarketStoreFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel

@Module
class MarketScreenModule {

    @Provides
    @ScreenScope
    fun provideMarketStore(
        managers: Map<Exchange, @JvmSuppressWildcards CurrencyPairsManager>
    ): MarketStore =
        MarketStoreFactory(DefaultStoreFactory, managers).create()

    @Provides
    @ScreenScope
    fun provideEventBus(): BroadcastChannel<OneTimeEvent> =
        BroadcastChannel(Channel.BUFFERED)
}
