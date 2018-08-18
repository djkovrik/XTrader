package com.sedsoftware.binance.common.params

import com.sedsoftware.core.entity.Exchange

enum class CurrentExchange(override val label: String) : Exchange {
    BINANCE("Binance")
}
