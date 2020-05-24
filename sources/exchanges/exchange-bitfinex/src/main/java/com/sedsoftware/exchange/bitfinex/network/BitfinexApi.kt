package com.sedsoftware.exchange.bitfinex.network

import com.sedsoftware.exchange.bitfinex.network.model.BitfinexPairTick
import retrofit2.http.GET
import retrofit2.http.Query

interface BitfinexApi {

    @GET("/v1/symbols")
    suspend fun getCurrencyPairs(): List<String>

    @GET("/v1/pubticker")
    suspend fun getPairTick(@Query("symbol") symbol: String): BitfinexPairTick
}
