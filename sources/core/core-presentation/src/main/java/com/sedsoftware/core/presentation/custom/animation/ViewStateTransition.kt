package com.sedsoftware.core.presentation.custom.animation

import android.view.View

interface ViewStateTransition {

    interface Callback {
        fun completed()
    }

    val from: View?
    val to: View?

    fun run(callback: Callback)
}
