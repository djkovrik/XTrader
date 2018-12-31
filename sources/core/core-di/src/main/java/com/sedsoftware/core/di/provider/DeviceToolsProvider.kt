package com.sedsoftware.core.di.provider

import android.content.Context
import com.sedsoftware.core.tools.api.Logger
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.core.tools.api.Signer
import com.sedsoftware.core.tools.api.Executor
import com.sedsoftware.core.di.App

interface DeviceToolsProvider {
    fun provideApp(): App
    fun provideContext(): Context
    fun provideExecutor(): Executor
    fun provideLogger(): Logger
    fun provideSettings(): Settings
    fun provideSigner(): Signer
}
