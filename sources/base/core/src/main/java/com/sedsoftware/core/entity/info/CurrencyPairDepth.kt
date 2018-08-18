package com.sedsoftware.core.entity.info

import com.sedsoftware.core.entity.CurrencyPair

interface CurrencyPairDepth {
    val pair: CurrencyPair
    val amount: Float
    val price: Float
    val total: Float
    val isBid: Boolean
}
