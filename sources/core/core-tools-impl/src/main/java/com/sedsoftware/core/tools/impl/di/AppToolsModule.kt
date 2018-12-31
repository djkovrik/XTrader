package com.sedsoftware.core.tools.impl.di

import com.sedsoftware.core.tools.api.Executor
import com.sedsoftware.core.tools.api.Logger
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.core.tools.api.Signer
import com.sedsoftware.core.tools.impl.encrypt.StringSigner
import com.sedsoftware.core.tools.impl.executor.AppExecutor
import com.sedsoftware.core.tools.impl.log.AppLogger
import com.sedsoftware.core.tools.impl.settings.AppSettings
import dagger.Binds
import dagger.Module

@Module
abstract class AppToolsModule {

    @Binds
    abstract fun provideExecutor(implementation: AppExecutor): Executor

    @Binds
    abstract fun provideLogger(implementation: AppLogger): Logger

    @Binds
    abstract fun provideSettings(implementation: AppSettings): Settings

    @Binds
    abstract fun provideSigner(implementation: StringSigner): Signer
}
