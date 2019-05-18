package com.sedsoftware.screens.main.navigation

import androidx.navigation.NavOptions
import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.navigation.destination.Destination
import com.sedsoftware.screens.main.R
import javax.inject.Inject

@ActivityScope
class ActualIntroCoordinator @Inject constructor(
    private val router: Router
) : IntroCoordinator {

    private val options: NavOptions by lazy {
        NavOptions.Builder()
            .setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
            .setPopUpTo(R.id.introScreenFragment, true)
            .build()
    }

    override fun navigateToHome() {
        router.navigateTo(
            Destination(
                routeId = R.id.navigate_from_intro_to_market,
                routeOptions = options
            )
        )
    }
}
