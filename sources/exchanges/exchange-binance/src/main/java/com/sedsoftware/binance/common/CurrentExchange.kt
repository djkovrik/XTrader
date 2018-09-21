package com.sedsoftware.binance.common

import com.sedsoftware.coreentity.Exchange

enum class CurrentExchange(override val label: String) : Exchange {
    BINANCE("Binance")
}
