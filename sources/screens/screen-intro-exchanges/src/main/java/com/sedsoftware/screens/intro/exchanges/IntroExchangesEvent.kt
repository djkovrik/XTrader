package com.sedsoftware.screens.intro.exchanges

sealed class IntroExchangesEvent {
    data class HandleError(val throwable: Throwable) : IntroExchangesEvent()
}
