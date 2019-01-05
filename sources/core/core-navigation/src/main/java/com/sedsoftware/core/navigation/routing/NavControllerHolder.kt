package com.sedsoftware.core.navigation.routing

import androidx.navigation.NavController

interface NavControllerHolder {
    fun setNavController(controller: NavController)
    fun removeNavController()
}
