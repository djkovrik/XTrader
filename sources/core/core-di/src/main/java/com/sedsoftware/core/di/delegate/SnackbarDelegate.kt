package com.sedsoftware.core.di.delegate

interface SnackbarDelegate {
    fun notify(textResId: Int)
    fun notifyWithAction(textResId: Int, buttonResId: Int, action: () -> Unit)
}
