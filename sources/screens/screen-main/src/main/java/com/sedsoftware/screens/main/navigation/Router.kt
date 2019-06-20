package com.sedsoftware.screens.main.navigation

import com.sedsoftware.screens.main.navigation.destination.Destination
import com.sedsoftware.screens.main.navigation.destination.DestinationsBuffer

class Router {

    private val destinationsBuffer = DestinationsBuffer()

    fun getNavControllerHolder(): NavControllerHolder =
        destinationsBuffer

    fun navigateTo(destination: Destination) {
        destinationsBuffer.navigateTo(destination)
    }
}
