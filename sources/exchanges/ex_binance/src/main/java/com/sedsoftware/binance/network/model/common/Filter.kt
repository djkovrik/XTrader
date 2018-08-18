package com.sedsoftware.binance.network.model.common

import com.sedsoftware.binance.common.params.Filters

data class Filter(
    val filterType: Filters,
    val minPrice: String?,
    val maxPrice: String?,
    val tickSize: String?,
    val minQty: String?,
    val maxQty: String?,
    val stepSize: String?,
    val minNotional: String?,
    val limit: String?,
    val maxNumAlgoOrders: String?
)
