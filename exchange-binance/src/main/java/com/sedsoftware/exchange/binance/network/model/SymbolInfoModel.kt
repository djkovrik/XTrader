package com.sedsoftware.exchange.binance.network.model

import com.sedsoftware.exchange.binance.common.params.OrderType
import com.sedsoftware.exchange.binance.common.params.SymbolStatus
import com.sedsoftware.exchange.binance.network.model.common.Filter

data class SymbolInfoModel(
    val symbol: String,
    val status: SymbolStatus,
    val baseAsset: String,
    val baseAssetPrecision: Int,
    val quoteAsset: String,
    val quotePrecision: Int,
    val orderTypes: List<OrderType>,
    val icebergAllowed: Boolean,
    val filters: List<Filter>
)
