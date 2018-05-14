package com.sedsoftware.xtrader.di.provider

import com.sedsoftware.xtrader.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor() : Provider<OkHttpClient> {

  private val loggingLevel: Level by lazy {
    if (BuildConfig.DEBUG) Level.BODY
    else Level.NONE
  }

  private val loggingInterceptor: HttpLoggingInterceptor by lazy {
    HttpLoggingInterceptor().setLevel(loggingLevel)
  }

  override fun get(): OkHttpClient =
    OkHttpClient
      .Builder()
      .addInterceptor(loggingInterceptor)
      .build()
}
