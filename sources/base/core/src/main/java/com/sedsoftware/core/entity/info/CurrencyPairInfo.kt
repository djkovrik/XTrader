package com.sedsoftware.core.entity.info

import com.sedsoftware.core.entity.CurrencyPair

interface CurrencyPairInfo : CurrencyPair {
    val minimumPrice: Float
    val minimumQuantity: Float
    val isActive: Boolean
}
