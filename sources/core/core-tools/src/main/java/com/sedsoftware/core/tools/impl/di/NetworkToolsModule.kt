package com.sedsoftware.core.tools.impl.di

import androidx.viewbinding.BuildConfig
import com.sedsoftware.core.tools.impl.adapter.OffsetDateTimeAdapter
import com.sedsoftware.core.tools.impl.checkIsNotMainThread
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkToolsModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) Level.BASIC else Level.NONE
        }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        checkIsNotMainThread("OkHttpClient builder")
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(OffsetDateTimeAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
}
