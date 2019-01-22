package com.sedsoftware.screens.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.sedsoftware.core.di.coordinator.SplashCoordinator
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.screens.main.R
import javax.inject.Inject

@ActivityScope
class ActualSplashCoordinator @Inject constructor(
    private val navController: NavController,
    private val settings: Settings
) : SplashCoordinator {

    private val navOptions: NavOptions by lazy {
        NavOptions.Builder()
            .setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
            .setPopUpTo(R.id.splashFragment, true)
            .build()
    }

    override fun navigateToNextScreen(navExtras: Navigator.Extras) {
        if (settings.isExchangesDownloaded) {
            navigateFromSplashToHome()
        } else {
            navigateFromSplashToIntro(navExtras)
        }
    }

    private fun navigateFromSplashToHome() {
        navController.navigate(R.id.navigate_from_splash_to_home, null, navOptions)
    }

    private fun navigateFromSplashToIntro(navExtras: Navigator.Extras) {
        navController.navigate(R.id.navigate_from_splash_to_intro, null, navOptions, navExtras)
    }
}
