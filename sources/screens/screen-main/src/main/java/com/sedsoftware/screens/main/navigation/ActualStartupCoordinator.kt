package com.sedsoftware.screens.main.navigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavOptions
import com.sedsoftware.core.di.coordinator.StartupCoordinator
import com.sedsoftware.core.di.delegate.NavigationFlowDelegate
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.navigation.destination.Destination
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.screens.main.R
import javax.inject.Inject

@ActivityScope
class ActualStartupCoordinator @Inject constructor(
    private val router: Router,
    private val settings: Settings,
    private val navigationFlowDelegate: NavigationFlowDelegate
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

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun navigateToNextScreen() {
        if (settings.isExchangesDownloaded) {
            navigateToHome()
        } else {
            navigateToIntro()
        }
    }

    private fun navigateToHome() {
        navigationFlowDelegate.switchToMainFlow()
    }

    private fun navigateToIntro() {
        router.navigateTo(
            Destination(
                routeId = R.id.navigate_from_startup_to_intro,
                routeOptions = options
            )
        )
    }
}
