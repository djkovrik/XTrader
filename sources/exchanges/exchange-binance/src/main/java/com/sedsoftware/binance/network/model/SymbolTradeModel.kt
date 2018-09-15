package com.sedsoftware.binance.network.model

data class SymbolTradeModel(
    val id: Long,
    val price: String,
    val qty: String,
    val time: Long,
    val isBuyerMaker: Boolean,
    val isBestMatch: Boolean
)
