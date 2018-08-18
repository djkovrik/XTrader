package com.sedsoftware.binance.network.model.dto

import com.sedsoftware.binance.common.params.Filters
import com.sedsoftware.binance.network.model.SymbolInfo
import com.sedsoftware.binance.network.model.common.RateLimit

data class PairsInfoDto(
    val timezone: String,
    val serverTime: Long,
    val rateLimits: RateLimit,
    val exchangeFilters: List<Filters>,
    val symbols: List<SymbolInfo>
)
