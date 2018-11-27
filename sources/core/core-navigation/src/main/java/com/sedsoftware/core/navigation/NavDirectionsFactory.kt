package com.sedsoftware.core.navigation

import android.os.Bundle
import androidx.navigation.NavDirections

interface NavDirectionsFactory {
    fun create(route: NavRoutes, arguments: Bundle? = null): NavDirections
}
