package com.sedsoftware.coreapi.entity.info

import com.sedsoftware.coreapi.entity.CurrencyPair

interface CurrencyPairInfo : CurrencyPair {
    val minimumPrice: Float
    val minimumQuantity: Float
    val isActive: Boolean
}
