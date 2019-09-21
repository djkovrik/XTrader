package com.sedsoftware.exchange.binance.di.module

import com.sedsoftware.exchange.binance.network.BinanceApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class BinanceNetworkModule {

    companion object {
        private const val BASE_URL = "https://api.binance.com/"
    }

    @Provides
    @Singleton
    fun provideBinanceApi(moshi: Moshi, client: OkHttpClient): BinanceApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(BinanceApi::class.java)
}
