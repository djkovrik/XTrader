package com.sedsoftware.screens.intro.base

sealed class OneTimeEvent {
    data class HandleError(val throwable: Throwable) : OneTimeEvent()
}
