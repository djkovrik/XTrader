package com.sedsoftware.screens.splash.navigation

import com.sedsoftware.core.di.scope.FragmentScope
import com.sedsoftware.core.navigation.NavRoutes
import com.sedsoftware.core.navigation.coordinator.SplashCoordinator
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import com.sedsoftware.core.navigation.holder.NavControllerHolder
import com.sedsoftware.core.navigation.holder.NavDirectionsFactoryHolder
import com.sedsoftware.core.tools.api.Settings
import javax.inject.Inject

@FragmentScope
class RealSplashCoordinator @Inject constructor(
    override val navController: NavControllerHolder,
    private val directionsFactory: NavDirectionsFactoryHolder,
    private val settings: Settings
) : SplashCoordinator {

    private val directions: NavDirectionsFactory by lazy {
        directionsFactory.get()
    }

    override fun start() {
        if (settings.isExchangesDownloaded) {
            navigateToHome()
        } else {
            navigateToIntro()
        }
    }

    override fun navigateToHome() {
        navController.get().navigate(directions.create(NavRoutes.Splash.ToHome))
    }

    override fun navigateToIntro() {
        navController.get().navigate(directions.create(NavRoutes.Splash.ToIntro))
    }

    override fun navigateToPin() {
        // TODO
    }
}
