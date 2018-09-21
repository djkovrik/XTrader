package com.sedsoftware.coreentity.info

import com.sedsoftware.coreentity.Exchange

interface CurrencyPairTick {
    val exchange: Exchange
    val symbol: String
    val bidPrice: Float
    val bidQuantity: Float
    val askPrice: Float
    val askQuantity: Float
}
