package com.sedsoftware.binance.network.model.dto

data class PairDepthItem(
    val price: String,
    val amount: String,
    val list: List<String>
)
