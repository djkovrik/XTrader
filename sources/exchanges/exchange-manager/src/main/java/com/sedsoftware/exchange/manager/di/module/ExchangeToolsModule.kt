package com.sedsoftware.exchange.manager.di.module

import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.exchange.manager.provider.ExchangeAssetsProvider
import dagger.Binds
import dagger.Module

@Module
interface ExchangeToolsModule {

    @Binds
    fun bindAssetsProvider(implementation: ExchangeAssetsProvider): AssetsProvider
}
