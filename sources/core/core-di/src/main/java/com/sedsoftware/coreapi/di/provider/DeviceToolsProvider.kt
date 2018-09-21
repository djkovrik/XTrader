package com.sedsoftware.coreapi.di.provider

import com.sedsoftware.coreapi.App
import com.sedsoftware.coredeviceapi.device.Logger
import com.sedsoftware.coredeviceapi.device.Settings
import com.sedsoftware.coredeviceapi.device.Signer
import com.sedsoftware.coredeviceapi.executor.Executor

interface DeviceToolsProvider {

    fun provideContext(): App

    fun provideExecutor(): Executor

    fun provideLogger(): Logger

    fun provideSettings(): Settings

    fun provideSigner(): Signer

}
