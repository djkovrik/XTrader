package com.sedsoftware.screens.splash.navigation

import com.sedsoftware.core.device.api.Settings
import com.sedsoftware.core.di.scope.FragmentScope
import com.sedsoftware.core.navigation.NavRoutes
import com.sedsoftware.core.navigation.coordinator.SplashCoordinator
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import com.sedsoftware.core.navigation.holder.NavControllerHolder
import com.sedsoftware.core.navigation.holder.NavDirectionsFactoryHolder
import javax.inject.Inject

@FragmentScope
class RealSplashCoordinator @Inject constructor(
    override val navHolder: NavControllerHolder,
    private val factoryHolder: NavDirectionsFactoryHolder,
    private val settings: Settings
) : SplashCoordinator {

    private val directions: NavDirectionsFactory by lazy {
        factoryHolder.getNavDirectionsFactory()
    }

    override fun start() {
        if (settings.isExchangesDownloaded) {
            navigateToHome()
        } else {
            navigateToIntro()
        }
    }

    override fun navigateToHome() {
        navHolder.getNavController().navigate(directions.create(NavRoutes.Splash.ToHome))
    }

    override fun navigateToIntro() {
        navHolder.getNavController().navigate(directions.create(NavRoutes.Splash.ToIntro))
    }

    override fun navigateToPin() {
        // TODO
    }
}
