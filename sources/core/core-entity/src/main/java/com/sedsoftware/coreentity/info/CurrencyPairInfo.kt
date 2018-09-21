package com.sedsoftware.coreentity.info

import com.sedsoftware.coreentity.CurrencyPair

interface CurrencyPairInfo : CurrencyPair {
    val minimumPrice: Float
    val minimumQuantity: Float
    val isActive: Boolean
}
