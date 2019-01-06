package com.sedsoftware.core.navigation.destination

import com.sedsoftware.core.navigation.NavigationRoute

interface DestinationsFactory {
    fun create(route: NavigationRoute): Destination
}
