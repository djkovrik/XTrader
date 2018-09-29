package com.sedsoftware.exchange.binance.network.model

data class SymbolTickModel(
    val symbol: String,
    val bidPrice: String,
    val bidQty: String,
    val askPrice: String,
    val askQty: String
)
