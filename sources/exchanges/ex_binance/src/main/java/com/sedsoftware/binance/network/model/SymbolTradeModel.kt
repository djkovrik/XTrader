package com.sedsoftware.binance.network.model

data class SymbolTradeModel(
    val id: Long,
    val price: Float,
    val qty: Float,
    val time: Long,
    val isBuyerMaker: Boolean,
    val isBestMatch: Boolean
)
