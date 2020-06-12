package com.sedsoftware.exchange.binance.di

import com.sedsoftware.exchange.binance.network.BinanceApi
import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Call
import okhttp3.Call.Factory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object BinanceNetworkModule {

    private const val BASE_URL = "https://api.binance.com/"

    @Provides
    fun provideBinanceApi(moshi: Moshi, client: Lazy<OkHttpClient>): BinanceApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callFactory(
                object : Factory {
                    override fun newCall(request: Request): Call {
                        return client.get().newCall(request)
                    }
                }
            )
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(BinanceApi::class.java)
}
