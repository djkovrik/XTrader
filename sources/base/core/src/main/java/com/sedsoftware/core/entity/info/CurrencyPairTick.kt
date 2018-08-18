package com.sedsoftware.core.entity.info

import com.sedsoftware.core.entity.Exchange

interface CurrencyPairTick {
    val exchange: Exchange
    val symbol: String
    val bidPrice: Float
    val bidQuantity: Float
    val askPrice: Float
    val askQuantity: Float
}
