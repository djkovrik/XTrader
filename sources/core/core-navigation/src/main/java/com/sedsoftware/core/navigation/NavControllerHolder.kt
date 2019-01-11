package com.sedsoftware.core.navigation

import androidx.navigation.NavController

interface NavControllerHolder {
    fun setNavController(newController: NavController)
    fun removeNavController()
}
