package com.sedsoftware.core.domain.entity.info

import com.sedsoftware.core.domain.entity.Exchange

interface CurrencyPairTick {
    val exchange: Exchange
    val symbol: String
    val bidPrice: Float
    val bidQuantity: Float
    val askPrice: Float
    val askQuantity: Float
}
