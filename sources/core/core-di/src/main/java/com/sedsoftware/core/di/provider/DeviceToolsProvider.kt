package com.sedsoftware.core.di.provider

import com.sedsoftware.core.di.App
import com.sedsoftware.core.tools.api.Executor
import com.sedsoftware.core.tools.api.Logger
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.core.tools.api.Signer

interface DeviceToolsProvider {
    fun provideApp(): App
    fun provideExecutor(): Executor
    fun provideLogger(): Logger
    fun provideSettings(): Settings
    fun provideSigner(): Signer
}
