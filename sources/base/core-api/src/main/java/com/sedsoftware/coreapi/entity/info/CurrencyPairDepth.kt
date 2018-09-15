package com.sedsoftware.coreapi.entity.info

import com.sedsoftware.coreapi.entity.CurrencyPair

interface CurrencyPairDepth {
    val pair: CurrencyPair
    val amount: Float
    val price: Float
    val total: Float
    val isBid: Boolean
}
