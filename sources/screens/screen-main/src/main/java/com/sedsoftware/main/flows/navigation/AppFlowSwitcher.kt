package com.sedsoftware.main.flows.navigation

import com.sedsoftware.core.domain.navigation.FlowSwitcher
import com.sedsoftware.main.Screens
import javax.inject.Inject

class AppFlowSwitcher @Inject constructor(
    private val flowRouter: GlobalFlowRouter
) : FlowSwitcher {

    override fun switchToStartingFlow() {
        flowRouter.newRootFlow(Screens.StartingFlow)
    }

    override fun switchToRegularFlow() {
        flowRouter.newRootFlow(Screens.RegularFlow)
    }
}
