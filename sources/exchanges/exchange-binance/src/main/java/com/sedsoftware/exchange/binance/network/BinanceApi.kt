package com.sedsoftware.exchange.binance.network

import com.sedsoftware.exchange.binance.network.model.dto.PairsInfo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface BinanceApi {

    @GET("/api/v1/exchangeInfo")
    fun getCurrencyPairs(): Deferred<PairsInfo>
}
