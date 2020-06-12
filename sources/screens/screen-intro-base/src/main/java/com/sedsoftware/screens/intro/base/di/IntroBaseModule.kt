package com.sedsoftware.screens.intro.base.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.core.domain.navigation.StartingFlowCoordinator
import com.sedsoftware.screens.intro.base.store.IntroBaseStore
import com.sedsoftware.screens.intro.base.store.IntroBaseStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object IntroBaseModule {

    @Provides
    @FragmentScoped
    fun provideIntroBaseStore(loader: CurrencyMapLoader, coordinator: StartingFlowCoordinator): IntroBaseStore =
        IntroBaseStoreFactory(DefaultStoreFactory, loader, coordinator).create()
}
