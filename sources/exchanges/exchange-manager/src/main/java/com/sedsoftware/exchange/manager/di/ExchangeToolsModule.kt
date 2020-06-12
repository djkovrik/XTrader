package com.sedsoftware.exchange.manager.di

import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.exchange.manager.provider.ExchangeAssetsProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface ExchangeToolsModule {

    @Binds
    fun bindAssetsProvider(implementation: ExchangeAssetsProvider): AssetsProvider
}
