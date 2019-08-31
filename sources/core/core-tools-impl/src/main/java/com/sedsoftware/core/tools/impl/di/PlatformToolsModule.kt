package com.sedsoftware.core.tools.impl.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import android.view.Display
import android.view.WindowManager
import com.sedsoftware.core.di.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlatformToolsModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context =
        app.getApplicationContext()

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
    fun provideDisplay(context: Context): Display =
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
}
