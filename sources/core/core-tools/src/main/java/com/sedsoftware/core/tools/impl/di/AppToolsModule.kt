package com.sedsoftware.core.tools.impl.di

import com.sedsoftware.core.tools.impl.cicerone.MainCiceroneManager
import com.sedsoftware.core.tools.impl.encrypt.StringSigner
import com.sedsoftware.core.tools.impl.log.AppLogger
import com.sedsoftware.core.tools.impl.network.AppNetworkHandler
import com.sedsoftware.core.tools.impl.settings.AppSettings
import dagger.Binds
import dagger.Module

@Module
abstract class AppToolsModule {

    @Binds
    abstract fun provideLogger(implementation: AppLogger): com.sedsoftware.core.domain.tools.Logger

    @Binds
    abstract fun provideNetworkHandler(implementation: AppNetworkHandler): com.sedsoftware.core.domain.tools.NetworkHandler

    @Binds
    abstract fun provideSettings(implementation: AppSettings): com.sedsoftware.core.domain.tools.Settings

    @Binds
    abstract fun provideSigner(implementation: StringSigner): com.sedsoftware.core.domain.tools.Signer

    @Binds
    abstract fun provideCiceroneProvider(implementation: MainCiceroneManager): com.sedsoftware.core.domain.tools.CiceroneManager
}
