package com.sedsoftware.core.entity.info

interface CurrencyPairTrade {
    val id: Long
    val timestamp: Long
    val quantity: Float
    val price: Float
    val total: Float
    val isBuy: Boolean
}
