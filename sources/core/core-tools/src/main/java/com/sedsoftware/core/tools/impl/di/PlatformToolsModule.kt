package com.sedsoftware.core.tools.impl.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import android.view.Display
import android.view.WindowManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PlatformToolsModule {

    @Provides
    @Singleton
    fun provideResources(@ApplicationContext context: Context): Resources =
        context.resources

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideDisplay(@ApplicationContext context: Context): Display =
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
}
