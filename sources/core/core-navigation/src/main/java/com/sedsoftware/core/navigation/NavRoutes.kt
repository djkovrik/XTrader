package com.sedsoftware.core.navigation

sealed class NavRoutes {

    sealed class Splash {
        object ToIntro : NavRoutes()
        object ToHome : NavRoutes()
        object ToPin : NavRoutes()
    }
}
