package com.sedsoftware.coreentity.info

import com.sedsoftware.coreentity.CurrencyPair

interface CurrencyPairDepth {
    val pair: CurrencyPair
    val amount: Float
    val price: Float
    val total: Float
    val isBid: Boolean
}
