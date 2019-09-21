package com.sedsoftware.exchange.manager.network.model

import com.squareup.moshi.Json

data class CurrencyItem(
    val id: Int,
    val name: String,
    val symbol: String,
    @Json(name = "is_active")
    val isActive: Int
)
