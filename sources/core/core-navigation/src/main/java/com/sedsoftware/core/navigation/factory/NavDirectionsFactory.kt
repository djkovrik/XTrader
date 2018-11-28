package com.sedsoftware.core.navigation.factory

import android.os.Bundle
import androidx.navigation.NavDirections
import com.sedsoftware.core.navigation.NavRoutes

interface NavDirectionsFactory {
    fun create(route: NavRoutes, arguments: Bundle? = null): NavDirections
}
