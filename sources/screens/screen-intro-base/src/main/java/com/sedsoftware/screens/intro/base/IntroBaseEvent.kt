package com.sedsoftware.screens.intro.base

sealed class IntroBaseEvent {
    data class HandleError(val throwable: Throwable) : IntroBaseEvent()
}
