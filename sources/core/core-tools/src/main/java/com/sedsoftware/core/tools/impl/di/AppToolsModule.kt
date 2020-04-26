package com.sedsoftware.core.tools.impl.di

import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.core.domain.tools.CiceroneManager
import com.sedsoftware.core.domain.tools.Logger
import com.sedsoftware.core.domain.tools.NetworkHandler
import com.sedsoftware.core.domain.tools.ResourceManager
import com.sedsoftware.core.domain.tools.Settings
import com.sedsoftware.core.domain.tools.Signer
import com.sedsoftware.core.tools.impl.cicerone.MainCiceroneManager
import com.sedsoftware.core.tools.impl.encrypt.StringSigner
import com.sedsoftware.core.tools.impl.errorhandler.DefaultErrorHandler
import com.sedsoftware.core.tools.impl.log.AppLogger
import com.sedsoftware.core.tools.impl.manager.AppResourceManager
import com.sedsoftware.core.tools.impl.network.AppNetworkHandler
import com.sedsoftware.core.tools.impl.settings.AppSettings
import dagger.Binds
import dagger.Module

@Module
abstract class AppToolsModule {

    @Binds
    abstract fun provideLogger(implementation: AppLogger): Logger

    @Binds
    abstract fun provideNetworkHandler(implementation: AppNetworkHandler): NetworkHandler

    @Binds
    abstract fun provideSettings(implementation: AppSettings): Settings

    @Binds
    abstract fun provideSigner(implementation: StringSigner): Signer

    @Binds
    abstract fun provideCiceroneProvider(implementation: MainCiceroneManager): CiceroneManager

    @Binds
    abstract fun provideResourceManager(implementation: AppResourceManager): ResourceManager

    @Binds
    abstract fun provideErrorHandler(implementation: DefaultErrorHandler): ErrorHandler
}
