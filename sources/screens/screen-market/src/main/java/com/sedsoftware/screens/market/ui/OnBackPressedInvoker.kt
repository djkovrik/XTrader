package com.sedsoftware.screens.market.ui

import javax.inject.Inject

class OnBackPressedInvoker @Inject constructor() {

    var action: () -> Unit = {}

    fun runAction() {
        action.invoke()
    }
}
