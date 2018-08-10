package com.sedsoftware.binance.network

import com.sedsoftware.binance.network.model.SymbolTick
import com.sedsoftware.binance.network.model.SymbolTrade
import com.sedsoftware.binance.network.model.dto.PairDepthDto
import com.sedsoftware.binance.network.model.dto.PairsInfoDto
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface BinanceApi {

    @GET("/api/v1/exchangeInfo")
    fun getCurrencyPairs(): Deferred<PairsInfoDto>

    @GET("/api/v1/depth")
    fun getPairDepth(@Query("symbol") symbol: String): Deferred<PairDepthDto>

    @GET("/api/v3/ticker/bookTicker")
    fun getPairTick(@Query("symbol") symbol: String): Deferred<SymbolTick>

    @GET("/api/v1/trades")
    fun getPairTrades(@Query("symbol") symbol: String): Deferred<List<SymbolTrade>>
}
