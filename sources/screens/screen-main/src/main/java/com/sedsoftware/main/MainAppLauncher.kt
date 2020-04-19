package com.sedsoftware.main

import com.sedsoftware.core.domain.tools.CiceroneManager
import com.sedsoftware.core.domain.tools.Settings
import com.sedsoftware.main.flows.AppFlow
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainAppLauncher @Inject constructor(
    private val ciceroneManager: CiceroneManager,
    private val settings: Settings
) {

    private val globalRouter: Router by lazy {
        ciceroneManager.getRouter(AppFlow.GLOBAL)
    }

    fun coldStart() {
        val rootScreen =
            if (settings.isAnyExchangeDownloaded) {
                Screens.RegularFlow
            } else {
                Screens.StartingFlow
            }

        globalRouter.newRootScreen(rootScreen)
    }
}
