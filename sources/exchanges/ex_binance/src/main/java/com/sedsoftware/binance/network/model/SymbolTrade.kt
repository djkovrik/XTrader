package com.sedsoftware.binance.network.model

data class SymbolTrade(
    val id: Long,
    val price: Float,
    val qty: Float,
    val time: Long,
    val isBuyerMaker: Boolean,
    val isBestMatch: Boolean
)
