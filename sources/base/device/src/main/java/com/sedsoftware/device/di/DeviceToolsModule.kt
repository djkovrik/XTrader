package com.sedsoftware.device.di

import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import com.sedsoftware.coreapi.App
import com.sedsoftware.coreapi.device.Logger
import com.sedsoftware.coreapi.device.Settings
import com.sedsoftware.coreapi.device.Signer
import com.sedsoftware.coreapi.executor.Executor
import com.sedsoftware.device.encrypt.StringSigner
import com.sedsoftware.device.executor.AppExecutor
import com.sedsoftware.device.log.AppLogger
import com.sedsoftware.device.settings.AppSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DeviceToolsModule {

    @Provides
    @Singleton
    fun provideExecutor(): Executor =
        AppExecutor()

    @Provides
    @Singleton
    fun provideLogger(): Logger =
        AppLogger()

    @Provides
    @Singleton
    fun provideResources(app: App): Resources =
        app.getApplicationContext().resources

    @Provides
    @Singleton
    fun provideSharedPreferences(app: App): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext())

    @Provides
    @Singleton
    fun provideSettings(resources: Resources, preferences: SharedPreferences): Settings =
        AppSettings(resources, preferences)

    @Provides
    @Singleton
    fun provideSigner(): Signer =
        StringSigner()
}
