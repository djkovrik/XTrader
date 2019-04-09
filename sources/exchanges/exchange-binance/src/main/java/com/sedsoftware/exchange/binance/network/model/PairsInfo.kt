package com.sedsoftware.exchange.binance.network.model

import com.sedsoftware.exchange.binance.network.model.params.FilterType
import com.sedsoftware.exchange.binance.network.model.params.RateLimit
import org.threeten.bp.OffsetDateTime

data class PairsInfo(
    val timezone: String,
    val serverTime: OffsetDateTime,
    val rateLimits: List<RateLimit>,
    val exchangeFilters: List<FilterType>,
    val symbols: List<SymbolInfoModel>
)
