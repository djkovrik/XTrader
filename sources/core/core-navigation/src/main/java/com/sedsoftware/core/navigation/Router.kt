package com.sedsoftware.core.navigation

import com.sedsoftware.core.navigation.destination.Destination
import com.sedsoftware.core.navigation.destination.DestinationBuffer

class Router {

    private val destinationsBuffer = DestinationBuffer()

    fun getNavControllerHolder(): NavControllerHolder =
        destinationsBuffer

    fun navigateTo(destination: Destination) {
        destinationsBuffer.goToDestination(destination)
    }
}
