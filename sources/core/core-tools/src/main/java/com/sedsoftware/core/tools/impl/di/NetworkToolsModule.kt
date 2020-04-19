package com.sedsoftware.core.tools.impl.di

import androidx.viewbinding.BuildConfig
import com.sedsoftware.core.tools.impl.checkIfMainThread
import com.sedsoftware.core.tools.impl.adapter.OffsetDateTimeAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import javax.inject.Singleton

@Module
class NetworkToolsModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) Level.BASIC else Level.NONE
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        checkIfMainThread("OkHttpClient builder")
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(OffsetDateTimeAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
}
