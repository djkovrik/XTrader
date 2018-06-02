package com.sedsoftware.core.di.provider

import com.sedsoftware.core.App
import com.sedsoftware.core.device.Logger
import com.sedsoftware.core.device.Signer

interface DeviceToolsProvider {

  fun provideContext(): App

  fun provideLogger(): Logger

  fun provideSigner(): Signer
}
