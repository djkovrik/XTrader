package com.sedsoftware.device.di

import com.sedsoftware.core.device.Logger
import com.sedsoftware.core.device.Signer
import com.sedsoftware.device.encrypt.SignerImpl
import com.sedsoftware.device.log.LoggerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DeviceToolsModule {

  @Module
  companion object {

    @JvmStatic
    @Provides
    @Singleton
    fun provideLogger(): Logger = LoggerImpl()

    @JvmStatic
    @Provides
    @Singleton
    fun provideSigner(): Signer = SignerImpl()
  }
}
