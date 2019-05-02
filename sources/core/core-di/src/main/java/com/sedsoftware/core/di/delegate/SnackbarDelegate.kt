package com.sedsoftware.core.di.delegate

interface SnackbarDelegate {
    fun notifyTop(textResId: Int)
    fun notifyBottom(textResId: Int)
    fun notifyBottomWithAction(textResId: Int, buttonResId: Int, action: () -> Unit)
}
