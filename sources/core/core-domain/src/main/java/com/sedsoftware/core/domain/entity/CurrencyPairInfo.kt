package com.sedsoftware.core.domain.entity

interface CurrencyPairInfo : CurrencyPair {
    val minimumPrice: Float
    val minimumQuantity: Float
    val isActive: Boolean
}
