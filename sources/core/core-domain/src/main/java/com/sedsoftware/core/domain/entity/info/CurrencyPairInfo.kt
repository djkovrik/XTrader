package com.sedsoftware.core.domain.entity.info

import com.sedsoftware.core.domain.entity.CurrencyPair

interface CurrencyPairInfo : CurrencyPair {
    val minimumPrice: Float
    val minimumQuantity: Float
    val isActive: Boolean
}
