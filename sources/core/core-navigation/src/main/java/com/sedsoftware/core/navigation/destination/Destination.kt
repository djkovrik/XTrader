package com.sedsoftware.core.navigation.destination

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

interface Destination : NavDirections {
    fun getNavOptions(): NavOptions? = null
    fun getNavExtras(): Navigator.Extras? = null
}
