package com.sedsoftware.screens.splash

import com.sedsoftware.core.navigation.NavigationRoute
import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.navigation.destination.DestinationFactory
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.presentation.extension.getDestinations
import com.sedsoftware.core.tools.api.Settings
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val router: Router,
    private val settings: Settings
) : BaseViewModel() {

    private val destinations: DestinationFactory by lazy {
        getDestinations(SplashFragment::class.java)
    }

    fun navigateToNextDestination() {
        launch {
            delay(SPLASH_DELAY_MS)
            goToNextScreen()
        }
    }

    private fun goToNextScreen() {
        if (settings.isExchangesDownloaded) {
            router.navigateTo(destinations.create(NavigationRoute.Splash.ToHome))
        } else {
            router.navigateTo(destinations.create(NavigationRoute.Splash.ToIntro))
        }
    }

    private companion object {
        const val SPLASH_DELAY_MS = 3000L
    }
}
