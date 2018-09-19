package com.sedsoftware.device.di

import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import com.sedsoftware.coreapi.App
import com.sedsoftware.coreapi.device.Settings
import com.sedsoftware.device.settings.AppSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlatformToolsModule {

    @Provides
    @Singleton
    fun provideResources(app: App): Resources =
        app.getApplicationContext().resources

    @Provides
    @Singleton
    fun provideSharedPreferences(app: App): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext())
}
