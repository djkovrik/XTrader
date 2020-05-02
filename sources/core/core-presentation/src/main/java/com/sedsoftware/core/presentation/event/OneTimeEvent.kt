package com.sedsoftware.core.presentation.event

sealed class OneTimeEvent {
    data class HandleError(val throwable: Throwable) : OneTimeEvent()
}
