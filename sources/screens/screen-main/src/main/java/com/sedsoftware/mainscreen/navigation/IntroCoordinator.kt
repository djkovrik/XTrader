package com.sedsoftware.mainscreen.navigation

import androidx.navigation.NavController
import com.sedsoftware.coredeviceapi.device.Settings
import com.sedsoftware.coreui.navigation.Coordinator
import com.sedsoftware.mainscreen.R
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
