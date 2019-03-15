package com.sedsoftware.exchange.binance.network.model

import com.sedsoftware.exchange.binance.network.model.params.FilterType
import com.sedsoftware.exchange.binance.network.model.params.RateLimit

data class PairsInfo(
    val timezone: String,
    val serverTime: Long,
    val rateLimits: List<RateLimit>,
    val exchangeFilters: List<FilterType>,
    val symbols: List<SymbolInfoModel>
)
