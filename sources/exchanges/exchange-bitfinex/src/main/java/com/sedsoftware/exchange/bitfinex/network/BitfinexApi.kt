package com.sedsoftware.exchange.bitfinex.network

import retrofit2.http.GET

interface BitfinexApi {

    @GET("/v1/symbols")
    suspend fun getCurrencyPairs(): List<String>
}
