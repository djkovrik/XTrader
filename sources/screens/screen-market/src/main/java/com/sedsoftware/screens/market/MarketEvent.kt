package com.sedsoftware.screens.market

sealed class MarketEvent {
    data class HandleError(val throwable: Throwable) : MarketEvent()
}
