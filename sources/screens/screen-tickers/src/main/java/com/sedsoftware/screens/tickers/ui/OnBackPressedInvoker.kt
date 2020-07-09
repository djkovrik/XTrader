package com.sedsoftware.screens.tickers.ui

import javax.inject.Inject

class OnBackPressedInvoker @Inject constructor() {

    var action: () -> Unit = {}

    fun runAction() {
        action.invoke()
    }
}
