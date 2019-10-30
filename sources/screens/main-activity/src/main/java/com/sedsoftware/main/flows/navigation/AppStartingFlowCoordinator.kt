package com.sedsoftware.main.flows.navigation

import com.sedsoftware.core.di.qualifier.StartingFlow
import com.sedsoftware.core.domain.coordinator.StartingFlowCoordinator
import com.sedsoftware.main.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppStartingFlowCoordinator @Inject constructor(
    @StartingFlow private val router: Router
) : StartingFlowCoordinator {

    override fun navigateToPinScreen() {
        router.newRootScreen(Screens.Pin)
    }

    override fun navigateToBaseScreen() {
        router.newRootScreen(Screens.IntroBase)
    }

    override fun navigateToExchangeScreen() {
        router.newRootScreen(Screens.IntroExchanges)
    }
}
