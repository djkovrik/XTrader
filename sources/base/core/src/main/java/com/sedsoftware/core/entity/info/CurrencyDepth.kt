package com.sedsoftware.core.entity.info

import com.sedsoftware.core.entity.Currency

data class CurrencyDepth(
    val amount: Float,
    val price: Float,
    val total: Float,
    val currency: Currency
)
