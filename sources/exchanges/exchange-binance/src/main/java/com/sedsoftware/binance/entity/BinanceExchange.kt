package com.sedsoftware.binance.entity

import com.sedsoftware.coreapi.entity.Exchange

enum class BinanceExchange(override val label: String) : Exchange {
    BINANCE("Binance")
}
