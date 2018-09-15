package com.sedsoftware.coreapi.entity.info

import com.sedsoftware.coreapi.entity.CurrencyPair

interface CurrencyPairTrade {
    val pair: CurrencyPair
    val id: Long
    val timestamp: Long
    val quantity: Float
    val price: Float
    val total: Float
    val isBuy: Boolean
}
