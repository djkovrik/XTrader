package com.sedsoftware.core.navigation.routing

sealed class NavigationRoute {

    sealed class Splash {
        object ToIntro : NavigationRoute()
        object ToHome : NavigationRoute()
        object ToPin : NavigationRoute()
    }
}
