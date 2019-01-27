package com.sedsoftware.screens.main.navigation

import androidx.navigation.NavOptions
import com.sedsoftware.core.di.coordinator.StartupCoordinator
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.navigation.destination.Destination
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.screens.main.R
import javax.inject.Inject

@ActivityScope
class ActualStartupCoordinator @Inject constructor(
    private val router: Router,
    private val settings: Settings
) : StartupCoordinator {

    private val options: NavOptions by lazy {
        NavOptions.Builder()
            .setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
            .setPopUpTo(R.id.startupFragment, true)
            .build()
    }

    override fun navigateToNextScreen() {
        val nextRoute = if (settings.isExchangesDownloaded) {
            R.id.navigate_from_startup_to_home
        } else {
            R.id.navigate_from_startup_to_intro
        }

        router.navigateTo(
            Destination(
                routeId = nextRoute,
                routeOptions = options
            )
        )
    }
}
