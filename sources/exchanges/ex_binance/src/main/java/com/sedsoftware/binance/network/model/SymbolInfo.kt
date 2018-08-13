package com.sedsoftware.binance.network.model

import com.sedsoftware.binance.common.enums.Filters
import com.sedsoftware.binance.common.enums.OrderType
import com.sedsoftware.binance.common.enums.SymbolStatus

data class SymbolInfo(
    val symbol: String,
    val status: SymbolStatus,
    val baseAsset: String,
    val baseAssetPrecision: Int,
    val quoteAsset: String,
    val quotePrecision: Int,
    val orderTypes: List<OrderType>,
    val icebergAllowed: Boolean,
    val filters: List<Filters>
)
