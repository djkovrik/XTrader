package com.sedsoftware.screens.main.navigation

import androidx.navigation.NavController

interface NavControllerHolder {
    fun setNavController(controller: NavController)
    fun removeNavController()
}
