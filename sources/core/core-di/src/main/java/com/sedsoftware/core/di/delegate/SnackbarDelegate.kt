package com.sedsoftware.core.di.delegate

interface SnackbarDelegate {
    fun notify(textResId: Int)
    fun notify(prefixTextResId: Int, message: String?)
}
