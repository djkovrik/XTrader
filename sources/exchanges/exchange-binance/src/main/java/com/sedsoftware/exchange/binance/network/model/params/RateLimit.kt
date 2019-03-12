package com.sedsoftware.exchange.binance.network.model.params

data class RateLimit(
    val rateLimitType: RateLimitType,
    val interval: RateLimitInterval,
    val limit: Long
)
