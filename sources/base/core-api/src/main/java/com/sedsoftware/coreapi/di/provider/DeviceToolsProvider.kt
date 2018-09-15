package com.sedsoftware.coreapi.di.provider

import com.sedsoftware.coreapi.App
import com.sedsoftware.coreapi.device.Logger
import com.sedsoftware.coreapi.device.Settings
import com.sedsoftware.coreapi.device.Signer
import com.sedsoftware.coreapi.executor.Executor

interface DeviceToolsProvider {

    fun provideContext(): App

    fun provideExecutor(): Executor

    fun provideLogger(): Logger

    fun provideSettings(): Settings

    fun provideSigner(): Signer
}
