package com.sedsoftware.core.di.provider

import com.sedsoftware.core.device.api.Logger
import com.sedsoftware.core.device.api.Settings
import com.sedsoftware.core.device.api.Signer
import com.sedsoftware.core.device.api.executor.Executor
import com.sedsoftware.core.di.App

interface DeviceToolsProvider {
    fun provideContext(): App
    fun provideExecutor(): Executor
    fun provideLogger(): Logger
    fun provideSettings(): Settings
    fun provideSigner(): Signer

}
