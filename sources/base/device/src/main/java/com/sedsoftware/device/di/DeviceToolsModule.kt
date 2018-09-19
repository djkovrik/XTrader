package com.sedsoftware.device.di

import com.sedsoftware.coreapi.device.Logger
import com.sedsoftware.coreapi.device.Settings
import com.sedsoftware.coreapi.device.Signer
import com.sedsoftware.coreapi.executor.Executor
import com.sedsoftware.device.encrypt.StringSigner
import com.sedsoftware.device.executor.AppExecutor
import com.sedsoftware.device.log.AppLogger
import com.sedsoftware.device.settings.AppSettings
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
