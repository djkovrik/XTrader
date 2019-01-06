package com.sedsoftware.core.navigation

sealed class NavigationRoute {

    sealed class Splash {
        object ToIntro : NavigationRoute()
        object ToHome : NavigationRoute()
        object ToPin : NavigationRoute()
    }
}
