package com.sedsoftware.core.device.impl.di

import com.sedsoftware.core.device.api.Logger
import com.sedsoftware.core.device.api.Settings
import com.sedsoftware.core.device.api.Signer
import com.sedsoftware.core.device.api.executor.Executor
import com.sedsoftware.core.device.impl.encrypt.StringSigner
import com.sedsoftware.core.device.impl.executor.AppExecutor
import com.sedsoftware.core.device.impl.log.AppLogger
import com.sedsoftware.core.device.impl.settings.AppSettings
import dagger.Module
import dagger.Provides

@Module
class DeviceToolsModule {

    @Provides
    fun provideExecutor(executor: AppExecutor): Executor = executor

    @Provides
    fun provideLogger(logger: AppLogger): Logger = logger

    @Provides
    fun provideSettings(settings: AppSettings): Settings = settings

    @Provides
    fun provideSigner(signer: StringSigner): Signer = signer
}
