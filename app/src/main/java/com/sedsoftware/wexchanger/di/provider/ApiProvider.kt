package com.sedsoftware.wexchanger.di.provider

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.sedsoftware.data.network.WexApi
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class ApiProvider @Inject constructor(
  private val okHttpClient: OkHttpClient,
  private val moshi: Moshi
) : Provider<WexApi> {

  private companion object {
    private const val BASE_URL = "https://wex.nz/api/3/"
  }

  override fun get(): WexApi =
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
      .create(WexApi::class.java)
}