package com.sedsoftware.screens.main.navigation

import androidx.navigation.NavController
import com.sedsoftware.core.device.api.Settings
import com.sedsoftware.core.presentation.navigation.Coordinator
import com.sedsoftware.screens.main.R
import javax.inject.Inject

class IntroCoordinator @Inject constructor(
    private val settings: Settings
) : Coordinator {

    var navController: NavController? = null

    override fun start() {
        if (settings.isExchangesDownloaded) {
            navController?.navigate(R.id.navigate_from_splash_to_home)
        } else {
            navController?.navigate(R.id.navigate_from_splash_to_intro)
        }
    }

    fun showHomeScreen() {
        navController?.navigate(R.id.navigate_from_intro_to_home)
    }
}
