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
interface AppToolsModule {

    @Binds
    fun bindLogger(implementation: AppLogger): Logger

    @Binds
    fun bindNetworkHandler(implementation: AppNetworkHandler): NetworkHandler

    @Binds
    fun bindSettings(implementation: AppSettings): Settings

    @Binds
    fun bindSigner(implementation: StringSigner): Signer

    @Binds
    fun bindCiceroneProvider(implementation: MainCiceroneManager): CiceroneManager

    @Binds
    fun bindResourceManager(implementation: AppResourceManager): ResourceManager

    @Binds
    fun bindErrorHandler(implementation: DefaultErrorHandler): ErrorHandler
}
