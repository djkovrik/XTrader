package com.sedsoftware.core.navigation.coordinator

import com.sedsoftware.core.navigation.holder.NavControllerHolder

interface Coordinator {
    val navController: NavControllerHolder

    fun start()
}
