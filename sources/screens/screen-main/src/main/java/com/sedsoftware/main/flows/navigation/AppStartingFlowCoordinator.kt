package com.sedsoftware.main.flows.navigation

import com.sedsoftware.core.domain.navigation.StartingFlowCoordinator
import com.sedsoftware.core.presentation.navigation.AppFlow
import com.sedsoftware.core.presentation.navigation.MainCiceroneHolder
import com.sedsoftware.main.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppStartingFlowCoordinator @Inject constructor(
    private val ciceroneHolder: MainCiceroneHolder
) : StartingFlowCoordinator {

    private val startingRouter: Router by lazy {
        ciceroneHolder.getRouter(AppFlow.STARTING)
    }

    // TODO
    override fun navigateToPinScreen() {
        startingRouter.newRootScreen(Screens.Empty)
    }

    override fun navigateToBaseScreen() {
        startingRouter.newRootScreen(Screens.Empty)
    }

    override fun navigateToExchangeScreen() {
        startingRouter.newRootScreen(Screens.Empty)
    }
}
