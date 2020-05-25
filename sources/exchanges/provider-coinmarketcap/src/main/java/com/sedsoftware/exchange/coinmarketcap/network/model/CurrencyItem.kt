package com.sedsoftware.exchange.coinmarketcap.network.model

import com.squareup.moshi.Json

data class CurrencyItem(
    val id: Int,
    @Json(name="name")
    val label: String,
    @Json(name="symbol")
    val name: String
)
