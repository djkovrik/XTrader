package com.sedsoftware.exchange.binance.network.model.params

data class Filter(
    val filterType: FilterType,
    val minPrice: String? = null,
    val maxPrice: String? = null,
    val tickSize: String? = null,
    val minQty: String? = null,
    val maxQty: String? = null,
    val stepSize: String? = null,
    val minNotional: String? = null,
    val limit: Int? = null,
    val maxNumAlgoOrders: Int? = null
)
