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
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface AppToolsModule {

    @Binds
    fun provideLogger(implementation: AppLogger): Logger

    @Binds
    fun provideNetworkHandler(implementation: AppNetworkHandler): NetworkHandler

    @Binds
    fun provideSettings(implementation: AppSettings): Settings

    @Binds
    fun provideSigner(implementation: StringSigner): Signer

    @Binds
    fun provideCiceroneProvider(implementation: MainCiceroneManager): CiceroneManager

    @Binds
    fun provideResourceManager(implementation: AppResourceManager): ResourceManager

    @Binds
    fun provideErrorHandler(implementation: DefaultErrorHandler): ErrorHandler
}
