package com.sedsoftware.exchange.bitfinex.di.module

import com.sedsoftware.exchange.bitfinex.network.BitfinexApi
import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.Call
import okhttp3.Call.Factory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object BitfinexNetworkModule {

    private const val BASE_URL = "https://api.bitfinex.com/"

    @Provides
    @Singleton
    fun provideBitfinexApi(moshi: Moshi, client: Lazy<OkHttpClient>): BitfinexApi =
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
            .create(BitfinexApi::class.java)
}
