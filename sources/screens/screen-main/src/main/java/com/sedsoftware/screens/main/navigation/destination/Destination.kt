package com.sedsoftware.screens.main.navigation.destination

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

data class Destination(
    val routeId: Int,
    val routeArguments: Bundle? = null,
    val routeOptions: NavOptions? = null,
    val routeExtras: Navigator.Extras? = null
)
