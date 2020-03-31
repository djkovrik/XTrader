package com.sedsoftware.exchange.coinmarketcap.network.model

import com.squareup.moshi.Json

data class CurrencyMapStatus(
    val timestamp: String,
    @Json(name = "error_code")
    val errorCode: Int
)
