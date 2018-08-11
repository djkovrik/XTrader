package com.sedsoftware.core.entity.info

import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.Exchange

interface CurrencyPairTick {
    val pair: CurrencyPair
    val exchange: Exchange
    val bid: Float
    val ask: Float
    val last: Float
}
