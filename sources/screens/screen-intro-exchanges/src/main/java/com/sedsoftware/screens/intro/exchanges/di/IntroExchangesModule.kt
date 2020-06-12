package com.sedsoftware.screens.intro.exchanges.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.navigation.FlowSwitcher
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object IntroExchangesModule {

    @Provides
    @FragmentScoped
    fun provideIntroExchangesStore(
        flowSwitcher: FlowSwitcher,
        loaders: Map<ExchangeType, @JvmSuppressWildcards CurrencyPairsLoader>
    ): IntroExchangesStore =
        IntroExchangesStoreFactory(DefaultStoreFactory, flowSwitcher, loaders).create()
}
