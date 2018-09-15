package com.sedsoftware.coreapi.entity.info

import com.sedsoftware.coreapi.entity.Exchange

interface CurrencyPairTick {
    val exchange: Exchange
    val symbol: String
    val bidPrice: Float
    val bidQuantity: Float
    val askPrice: Float
    val askQuantity: Float
}
