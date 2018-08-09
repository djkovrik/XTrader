package com.sedsoftware.core.entity.info

import com.sedsoftware.core.common.enum.OrderType
import com.sedsoftware.core.entity.Currency

data class CurrencyTrade(
    val id: Long,
    val timestamp: Long,
    val price: Float,
    val total: Float,
    val type: OrderType,
    val currency: Currency
)
