package com.sedsoftware.binance.network.model.common

import com.sedsoftware.binance.common.params.RateLimitInterval
import com.sedsoftware.binance.common.params.RateLimitType

data class RateLimit(
    val rateLimitType: RateLimitType,
    val interval: RateLimitInterval,
    val limit: Long
)
