package com.sedsoftware.core.entity.info

import com.sedsoftware.core.entity.CurrencyPair

interface CurrencyPairTrade {
    val id: Long
    val pair: CurrencyPair
    val timestamp: Long
    val quantity: Float
    val price: Float
    val total: Float
    val isBuy: Boolean
}
