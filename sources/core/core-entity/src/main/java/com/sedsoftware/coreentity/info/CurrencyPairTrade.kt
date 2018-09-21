package com.sedsoftware.coreentity.info

import com.sedsoftware.coreentity.CurrencyPair

interface CurrencyPairTrade {
    val pair: CurrencyPair
    val id: Long
    val timestamp: Long
    val quantity: Float
    val price: Float
    val total: Float
    val isBuy: Boolean
}
