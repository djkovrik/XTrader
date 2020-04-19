package com.sedsoftware.screens.intro.base.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.di.scope.ScreenScope
import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.screens.intro.base.store.IntroBaseStore
import com.sedsoftware.screens.intro.base.store.IntroBaseStoreFactory
import dagger.Module
import dagger.Provides

@Module
class IntroBaseModule {

    @Provides
    @ScreenScope
    fun provideIntroBaseStore(currencyMapLoader: CurrencyMapLoader): IntroBaseStore =
        IntroBaseStoreFactory(DefaultStoreFactory, currencyMapLoader).create()
}
