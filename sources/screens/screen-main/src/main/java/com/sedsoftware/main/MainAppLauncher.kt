package com.sedsoftware.main

import com.sedsoftware.core.domain.tools.Settings
import com.sedsoftware.main.flows.navigation.GlobalFlowRouter
import javax.inject.Inject

class MainAppLauncher @Inject constructor(
    private val globalFlowRouter: GlobalFlowRouter,
    private val settings: Settings
) {

    fun coldStart() {
        val rootScreen =
            if (settings.isAnyExchangeDownloaded) {
                Screens.RegularFlow
            } else {
                Screens.StartingFlow
            }

        globalFlowRouter.newRootFlow(rootScreen)
    }
}
