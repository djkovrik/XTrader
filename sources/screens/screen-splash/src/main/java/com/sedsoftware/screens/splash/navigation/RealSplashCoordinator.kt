package com.sedsoftware.screens.splash.navigation

import com.sedsoftware.core.device.api.Settings
import com.sedsoftware.core.di.scope.FragmentScope
import com.sedsoftware.core.navigation.NavRoutes
import com.sedsoftware.core.navigation.coordinator.SplashCoordinator
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import com.sedsoftware.core.navigation.holder.NavControllerHolder
import javax.inject.Inject

@FragmentScope
class RealSplashCoordinator @Inject constructor(
    override val navControllerHolder: NavControllerHolder,
    private val directionsFactory: NavDirectionsFactory,
    private val settings: Settings
) : SplashCoordinator {

    override fun start() {
        if (settings.isExchangesDownloaded) {
            navigateToHome()
        } else {
            navigateToIntro()
        }
    }

    override fun navigateToHome() {
        navControllerHolder.getNavController().navigate(directionsFactory.create(NavRoutes.Splash.ToHome))
    }

    override fun navigateToIntro() {
        navControllerHolder.getNavController().navigate(directionsFactory.create(NavRoutes.Splash.ToIntro))
    }

    override fun navigateToPin() {
        // TODO
    }
}
