package com.sedsoftware.exchange.bitfinex.network.model

import com.squareup.moshi.Json

data class BitfinexPairTick(
    @Json(name = "last_price")
    val price: String
)
