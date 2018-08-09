package com.sedsoftware.core.entity.info

import com.sedsoftware.core.entity.Currency

data class CurrencyTick(
    val bid: Float,
    val ask: Float,
    val last: Float,
    val currency: Currency
)
