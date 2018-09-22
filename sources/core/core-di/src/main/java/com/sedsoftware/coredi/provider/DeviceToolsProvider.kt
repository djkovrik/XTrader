package com.sedsoftware.coredi.provider

import com.sedsoftware.coredeviceapi.device.Logger
import com.sedsoftware.coredeviceapi.device.Settings
import com.sedsoftware.coredeviceapi.device.Signer
import com.sedsoftware.coredeviceapi.executor.Executor
import com.sedsoftware.coredi.App

interface DeviceToolsProvider {
    fun provideContext(): App
    fun provideExecutor(): Executor
    fun provideLogger(): Logger
    fun provideSettings(): Settings
    fun provideSigner(): Signer

}
