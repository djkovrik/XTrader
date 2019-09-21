package com.sedsoftware.exchange.manager.network

import com.sedsoftware.exchange.manager.BuildConfig
import com.sedsoftware.exchange.manager.network.model.CurrencyMap
import retrofit2.http.GET
import retrofit2.http.Headers

interface CoinMarketCapApi {

    @Headers("$API_HEADER: ${BuildConfig.COIN_MARKET_CAP_API_KEY}")
    @GET("/v1/cryptocurrency/map")
    suspend fun getCurrencyMap(): CurrencyMap

    private companion object {
        const val API_HEADER = "X-CMC_PRO_API_KEY"
    }
}
