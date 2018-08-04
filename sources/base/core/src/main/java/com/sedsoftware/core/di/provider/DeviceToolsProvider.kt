package com.sedsoftware.core.di.provider

import com.sedsoftware.core.App
import com.sedsoftware.core.device.Logger
import com.sedsoftware.core.device.Settings
import com.sedsoftware.core.device.Signer
import com.sedsoftware.core.executor.Executor

interface DeviceToolsProvider {

    fun provideContext(): App

    fun provideExecutor(): Executor

    fun provideLogger(): Logger

    fun provideSettings(): Settings

    fun provideSigner(): Signer
}
