package com.sedsoftware.exchange.binance.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.sedsoftware.exchange.binance.network.BinanceApi
import dagger.Module
import dagger.Provides
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
    fun provideBinanceApi(): BinanceApi =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BinanceApi::class.java)
}
