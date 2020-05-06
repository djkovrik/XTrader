package com.sedsoftware.screens.intro.exchanges.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.navigation.FlowSwitcher
import com.sedsoftware.core.presentation.event.OneTimeEvent
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStoreFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel

@Module
class IntroExchangesModule {

    @Provides
    @ScreenScope
    fun provideIntroExchangesStore(
        flowSwitcher: FlowSwitcher,
        loaders: Map<Exchange, @JvmSuppressWildcards CurrencyPairsLoader>
    ): IntroExchangesStore =
        IntroExchangesStoreFactory(DefaultStoreFactory, flowSwitcher, loaders).create()

    @Provides
    @ScreenScope
    fun provideEventBus(): BroadcastChannel<OneTimeEvent> =
        BroadcastChannel(Channel.BUFFERED)
}