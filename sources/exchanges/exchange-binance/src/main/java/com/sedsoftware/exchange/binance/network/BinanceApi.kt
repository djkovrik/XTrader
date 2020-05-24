package com.sedsoftware.exchange.binance.network

import com.sedsoftware.exchange.binance.network.model.PairsInfo
import com.sedsoftware.exchange.binance.network.model.BinancePairTick
import retrofit2.http.GET
import retrofit2.http.Query

interface BinanceApi {

    @GET("/api/v3/exchangeInfo")
    suspend fun getCurrencyPairs(): PairsInfo

    @GET("/api/v3/ticker/price")
    suspend fun getPairTick(@Query("symbol") symbol: String): BinancePairTick
}
