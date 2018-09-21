package com.sedsoftware.coredeviceimpl.di

import com.sedsoftware.coredeviceapi.device.Logger
import com.sedsoftware.coredeviceapi.device.Settings
import com.sedsoftware.coredeviceapi.device.Signer
import com.sedsoftware.coredeviceapi.executor.Executor
import com.sedsoftware.coredeviceimpl.encrypt.StringSigner
import com.sedsoftware.coredeviceimpl.executor.AppExecutor
import com.sedsoftware.coredeviceimpl.log.AppLogger
import com.sedsoftware.coredeviceimpl.settings.AppSettings
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
