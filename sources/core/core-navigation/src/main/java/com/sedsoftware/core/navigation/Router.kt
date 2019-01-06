package com.sedsoftware.core.navigation

import com.sedsoftware.core.navigation.destination.Destination
import com.sedsoftware.core.navigation.destination.DestinationsBuffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Router @Inject constructor() {

    val destinationsBuffer = DestinationsBuffer()

    fun navigateTo(destination: Destination) {
        destinationsBuffer.goToDestination(destination)
    }
}
