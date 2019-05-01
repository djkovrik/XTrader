package com.sedsoftware.core.presentation.animation

interface ViewStateTransition {

    interface Callback {
        fun completed()
    }

    fun run(callback: Callback)
}
