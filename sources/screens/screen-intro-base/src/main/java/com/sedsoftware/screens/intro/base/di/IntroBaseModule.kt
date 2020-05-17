package com.sedsoftware.screens.intro.base.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.core.domain.navigation.StartingFlowCoordinator
import com.sedsoftware.screens.intro.base.IntroBaseEvent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore
import com.sedsoftware.screens.intro.base.store.IntroBaseStoreFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel

@Module
class IntroBaseModule {

    @Provides
    @ScreenScope
    fun provideIntroBaseStore(loader: CurrencyMapLoader, coordinator: StartingFlowCoordinator): IntroBaseStore =
        IntroBaseStoreFactory(DefaultStoreFactory, loader, coordinator).create()

    @Provides
    @ScreenScope
    fun provideEventBus(): BroadcastChannel<IntroBaseEvent> =
        BroadcastChannel(Channel.BUFFERED)
}
