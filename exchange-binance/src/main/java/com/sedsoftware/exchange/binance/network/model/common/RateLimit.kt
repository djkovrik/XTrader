package com.sedsoftware.exchange.binance.network.model.common

import com.sedsoftware.exchange.binance.common.params.RateLimitInterval
import com.sedsoftware.exchange.binance.common.params.RateLimitType

data class RateLimit(
    val rateLimitType: RateLimitType,
    val interval: RateLimitInterval,
    val limit: Long
)
