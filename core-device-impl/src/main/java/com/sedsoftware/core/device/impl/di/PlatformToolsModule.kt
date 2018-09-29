package com.sedsoftware.core.device.impl.di

import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import com.sedsoftware.core.di.App
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
