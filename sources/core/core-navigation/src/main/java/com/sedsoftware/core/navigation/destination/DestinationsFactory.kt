package com.sedsoftware.core.navigation.destination

import com.sedsoftware.core.navigation.routing.NavigationRoute

interface DestinationsFactory {
    fun create(route: NavigationRoute): Destination
}
