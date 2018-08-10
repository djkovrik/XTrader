package com.sedsoftware.binance.network.model.dto

data class PairDepthDto(
    val lastUpdateId: Long,
    val bids: List<Pair<String, String>>,
    val asks: List<Pair<String, String>>
)
