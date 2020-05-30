package com.sedsoftware.screens.intro.exchanges.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.navigation.FlowSwitcher
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStoreFactory
import dagger.Module
import dagger.Provides

@Module
class IntroExchangesModule {

    @Provides
    @ScreenScope
    fun provideIntroExchangesStore(
        flowSwitcher: FlowSwitcher,
        loaders: Map<Exchange, @JvmSuppressWildcards CurrencyPairsLoader>
    ): IntroExchangesStore =
        IntroExchangesStoreFactory(DefaultStoreFactory, flowSwitcher, loaders).create()
}
