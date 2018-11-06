package com.sedsoftware.core.device.impl.di

import com.sedsoftware.core.device.api.Logger
import com.sedsoftware.core.device.api.Settings
import com.sedsoftware.core.device.api.Signer
import com.sedsoftware.core.device.api.Executor
import com.sedsoftware.core.device.impl.encrypt.StringSigner
import com.sedsoftware.core.device.impl.executor.AppExecutor
import com.sedsoftware.core.device.impl.log.AppLogger
import com.sedsoftware.core.device.impl.settings.AppSettings
import dagger.Module
import dagger.Provides

@Module
class DeviceToolsModule {

    @Provides
    fun provideExecutor(executorImplementation: AppExecutor): Executor = executorImplementation

    @Provides
    fun provideLogger(loggerImplementation: AppLogger): Logger = loggerImplementation

    @Provides
    fun provideSettings(settingsImplementation: AppSettings): Settings = settingsImplementation

    @Provides
    fun provideSigner(signerImplementation: StringSigner): Signer = signerImplementation
}
