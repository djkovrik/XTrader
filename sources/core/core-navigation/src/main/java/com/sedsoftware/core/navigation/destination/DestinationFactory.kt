package com.sedsoftware.core.navigation.destination

import android.os.Bundle
import com.sedsoftware.core.navigation.NavigationRoute

interface DestinationFactory {
    fun create(route: NavigationRoute, arguments: Bundle? = null): Destination
}
