package com.sedsoftware.binance.common.params

import com.sedsoftware.coreapi.entity.Exchange

enum class CurrentExchange(override val label: String) : Exchange {
    BINANCE("Binance")
}
