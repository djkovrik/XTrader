package com.sedsoftware.core.navigation.routing

import com.sedsoftware.core.navigation.destination.Destination
import com.sedsoftware.core.navigation.destination.DestinationsBuffer


class Router {

    val destinationsBuffer = DestinationsBuffer()

    fun navigateTo(destination: Destination) {
        destinationsBuffer.goToDestination(destination)
    }
}
