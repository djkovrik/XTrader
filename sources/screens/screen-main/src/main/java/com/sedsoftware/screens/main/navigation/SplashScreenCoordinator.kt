package com.sedsoftware.screens.main.navigation

import com.sedsoftware.core.device.api.Settings
import com.sedsoftware.core.di.holder.NavControllerHolder
import com.sedsoftware.core.domain.navigation.SplashCoordinator
import com.sedsoftware.screens.main.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SplashScreenCoordinator @Inject constructor(
    private val navControllerHolder: NavControllerHolder,
    private val settings: Settings
) : SplashCoordinator {

    override fun navigateToNextAvailableScreen() {
        if (settings.isExchangesDownloaded) {
            navControllerHolder.getNavController().navigate(R.id.navigate_from_splash_to_home)
        } else {
            navControllerHolder.getNavController().navigate(R.id.navigate_from_splash_to_intro)
        }
    }
}
