package com.sedsoftware.binance.network.model

import com.sedsoftware.binance.enums.Filters
import com.sedsoftware.binance.enums.OrderType
import com.sedsoftware.binance.enums.SymbolStatus

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
