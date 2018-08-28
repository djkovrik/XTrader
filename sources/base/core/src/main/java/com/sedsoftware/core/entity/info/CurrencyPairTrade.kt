package com.sedsoftware.core.entity.info

import com.sedsoftware.core.entity.CurrencyPair

interface CurrencyPairTrade {
    val pair: CurrencyPair
    val id: Long
    val timestamp: Long
    val quantity: Float
    val price: Float
    val total: Float
    val isBuy: Boolean
}
