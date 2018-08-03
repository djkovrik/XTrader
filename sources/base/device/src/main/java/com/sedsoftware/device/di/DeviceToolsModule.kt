package com.sedsoftware.device.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import com.sedsoftware.core.device.Logger
import com.sedsoftware.core.device.Settings
import com.sedsoftware.core.device.Signer
import com.sedsoftware.device.encrypt.SignerImpl
import com.sedsoftware.device.log.LoggerImpl
import com.sedsoftware.device.settings.SettingsImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DeviceToolsModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger =
        LoggerImpl()

    @Provides
    @Singleton
    fun provideSigner(): Signer =
        SignerImpl()

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources =
        context.resources

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideSettings(resources: Resources, preferences: SharedPreferences): Settings =
        SettingsImpl(resources, preferences)
}
