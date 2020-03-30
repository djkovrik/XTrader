package com.sedsoftware.main.flows.navigation

import com.sedsoftware.core.domain.navigation.StartingFlowCoordinator
import com.sedsoftware.core.domain.tools.CiceroneManager
import com.sedsoftware.main.Screens
import com.sedsoftware.main.flows.AppFlow
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppStartingFlowCoordinator @Inject constructor(
    private val ciceroneManager: CiceroneManager
) : StartingFlowCoordinator {

    private val startingRouter: Router by lazy {
        ciceroneManager.getRouter(AppFlow.STARTING)
    }

    // TODO
    override fun navigateToPinScreen() {
        startingRouter.newRootScreen(Screens.Empty)
    }

    override fun navigateToBaseScreen() {
        startingRouter.newRootScreen(Screens.IntroBase)
    }

    override fun navigateToExchangeScreen() {
        startingRouter.newRootScreen(Screens.Empty)
    }
}
