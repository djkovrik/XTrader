package com.sedsoftware.screens.tickers

sealed class TickersEvent {
    data class HandleError(val throwable: Throwable) : TickersEvent()
}
