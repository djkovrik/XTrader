package com.sedsoftware.core.domain.entity.info

import com.sedsoftware.core.domain.entity.CurrencyPair

interface CurrencyPairDepth {
    val pair: CurrencyPair
    val amount: Float
    val price: Float
    val total: Float
    val isBid: Boolean
}
