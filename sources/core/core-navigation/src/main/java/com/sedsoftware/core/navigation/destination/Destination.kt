package com.sedsoftware.core.navigation.destination

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

interface Destination : NavDirections {
    val navOptions: NavOptions?
        get() = null

    val navExtras: Navigator.Extras?
        get() = null
}
