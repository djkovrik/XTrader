package com.sedsoftware.binance.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.sedsoftware.binance.network.BinanceApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
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
            .build()
            .create(BinanceApi::class.java)
}
