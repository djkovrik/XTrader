package com.sedsoftware.main.flows.navigation

import com.sedsoftware.core.domain.coordinator.FlowSwitcher
import com.sedsoftware.main.Screens
import com.sedsoftware.main.flows.FlowRouter
import javax.inject.Inject

class AppFlowSwitcher @Inject constructor(
    private val flowRouter: FlowRouter
) : FlowSwitcher {

    override fun switchToStartingFlow() {
        flowRouter.newRootFlow(Screens.StartingFlow)
    }

    override fun switchToRegularFlow() {
        flowRouter.newRootFlow(Screens.RegularFlow)
    }
}
