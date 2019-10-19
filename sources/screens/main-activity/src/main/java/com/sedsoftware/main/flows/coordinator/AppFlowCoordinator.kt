package com.sedsoftware.main.flows.coordinator

import com.sedsoftware.core.domain.coordinator.FlowCoordinator
import com.sedsoftware.main.Screens
import com.sedsoftware.main.flows.FlowRouter
import javax.inject.Inject

class AppFlowCoordinator @Inject constructor(
    private val flowRouter: FlowRouter
) : FlowCoordinator {

    override fun switchToStartingFlow() {
        flowRouter.newRootScreen(Screens.StartingFlow)
    }

    override fun switchToRegularFlow() {
        flowRouter.newRootScreen(Screens.RegularFlow)
    }
}
