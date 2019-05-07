package com.sedsoftware.exchange.binance.network

import com.sedsoftware.exchange.binance.network.model.PairsInfo
import retrofit2.http.GET

interface BinanceApi {

    @GET("/api/v1/exchangeInfo")
    suspend fun getCurrencyPairs(): PairsInfo
}
