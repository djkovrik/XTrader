package com.sedsoftware.core.device.impl.di

import com.sedsoftware.core.device.api.Executor
import com.sedsoftware.core.device.api.Logger
import com.sedsoftware.core.device.api.Settings
import com.sedsoftware.core.device.api.Signer
import com.sedsoftware.core.device.impl.encrypt.StringSigner
import com.sedsoftware.core.device.impl.executor.AppExecutor
import com.sedsoftware.core.device.impl.log.AppLogger
import com.sedsoftware.core.device.impl.settings.AppSettings
import dagger.Binds
import dagger.Module

@Module
abstract class DeviceToolsModule {

    @Binds
    abstract fun provideExecutor(executorImplementation: AppExecutor): Executor

    @Binds
    abstract fun provideLogger(loggerImplementation: AppLogger): Logger

    @Binds
    abstract fun provideSettings(settingsImplementation: AppSettings): Settings

    @Binds
    abstract fun provideSigner(signerImplementation: StringSigner): Signer
}
