package com.sedsoftware.binance.network.model.dto

import com.sedsoftware.binance.common.params.FilterType
import com.sedsoftware.binance.network.model.SymbolInfoModel
import com.sedsoftware.binance.network.model.common.RateLimit

data class PairsInfoDto(
    val timezone: String,
    val serverTime: Long,
    val rateLimits: List<RateLimit>,
    val exchangeFilters: List<FilterType>,
    val symbols: List<SymbolInfoModel>
)
