package com.sedsoftware.core.entity.info

import com.sedsoftware.core.entity.Currency

data class CurrencyPair(
    val baseCurrency: Currency,
    val marketCurrency: Currency,
    val minimumPrice: Float,
    val minimumQuantity: Float,
    val isActive: Boolean
)
