package com.sedsoftware.exchange.bitfinex.network

import retrofit2.http.GET

interface BitfinexApi {

    @GET("/v2/conf/pub:list:pair:exchange")
    suspend fun getCurrencyPairs(): List<List<String>>
}
