package com.sedsoftware.exchange.coinmarketcap.di.module

import com.sedsoftware.exchange.coinmarketcap.network.CoinMarketCapApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class CurrencyNetworkModule {

    companion object {
        private const val BASE_URL = "https://pro-api.coinmarketcap.com/"
    }

    @Provides
    @Singleton
    fun provideCoinMarketCapApi(moshi: Moshi, client: OkHttpClient): CoinMarketCapApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CoinMarketCapApi::class.java)
}
