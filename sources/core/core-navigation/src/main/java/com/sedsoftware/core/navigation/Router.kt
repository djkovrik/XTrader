package com.sedsoftware.core.navigation

import com.sedsoftware.core.navigation.destination.Destination
import com.sedsoftware.core.navigation.destination.DestinationsBuffer

class Router {

    private val destinationsBuffer = DestinationsBuffer()

    fun getNavControllerHolder(): NavControllerHolder =
        destinationsBuffer

    fun navigateTo(destination: Destination) {
        destinationsBuffer.navigateTo(destination)
    }
}
