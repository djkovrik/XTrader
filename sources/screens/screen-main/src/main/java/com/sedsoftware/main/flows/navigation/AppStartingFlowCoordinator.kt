package com.sedsoftware.main.flows.navigation

import com.sedsoftware.core.domain.navigation.StartingFlowCoordinator
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppStartingFlowCoordinator @Inject constructor(
    private val router: Router // starting
) : StartingFlowCoordinator {

    override fun navigateToPinScreen() {
//        router.newRootScreen(Screens.Pin)
    }

    override fun navigateToBaseScreen() {
//        router.newRootScreen(Screens.IntroBase)
    }

    override fun navigateToExchangeScreen() {
//        router.newRootScreen(Screens.IntroExchanges)
    }
}
