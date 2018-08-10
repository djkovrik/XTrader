package com.sedsoftware.binance.network.model

data class SymbolTick(
    val symbol: String,
    val bidPrice: String,
    val bidQty: String,
    val askPrice: String,
    val askQty: String
)
