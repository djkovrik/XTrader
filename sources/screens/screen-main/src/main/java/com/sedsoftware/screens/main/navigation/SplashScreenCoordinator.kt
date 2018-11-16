package com.sedsoftware.screens.main.navigation

import com.sedsoftware.core.device.api.Settings
import com.sedsoftware.core.di.holder.NavControllerHolder
import com.sedsoftware.core.di.scope.PerScreen
import com.sedsoftware.core.domain.navigation.SplashCoordinator
import javax.inject.Inject

@PerScreen
class SplashScreenCoordinator @Inject constructor(
    private val navControllerHolder: NavControllerHolder,
    private val settings: Settings
) : SplashCoordinator {

    override fun navigateToNextAvailableScreen() {

    }
}
