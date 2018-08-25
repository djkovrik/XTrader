package com.sedsoftware.binance.network.model.common

data class RateLimit(
    val rateLimitType: String,
    val interval: String,
    val limit: Long
)
