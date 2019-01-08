package com.sedsoftware.core.navigation

import com.sedsoftware.core.navigation.destination.Destination
import com.sedsoftware.core.navigation.destination.DestinationBuffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Router @Inject constructor() {

    val destinationsBuffer = DestinationBuffer()

    fun navigateTo(destination: Destination) {
        destinationsBuffer.goToDestination(destination)
    }
}
