package com.sedsoftware.core.presentation.navigation

sealed class NavRoutes {

    sealed class Splash {
        object ToIntro : NavRoutes()
        object ToHome : NavRoutes()
        object ToPin : NavRoutes()
    }
}
