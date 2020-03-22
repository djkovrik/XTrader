package com.sedsoftware.exchange.coinmarketcap.di.module

import com.sedsoftware.exchange.coinmarketcap.network.CoinMarketCapApi
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
class CoinMarketCapNetworkModule {

    companion object {
        private const val BASE_URL = "https://pro-api.coinmarketcap.com/"
    }

    @Provides
    @Singleton
    fun provideCoinMarketCapApi(moshi: Moshi, client: Lazy<OkHttpClient>): CoinMarketCapApi =
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
            .create(CoinMarketCapApi::class.java)
}
