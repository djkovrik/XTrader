package com.sedsoftware.main

import com.sedsoftware.core.presentation.navigation.AppFlow
import com.sedsoftware.core.presentation.navigation.MainCiceroneHolder
import com.sedsoftware.core.tools.api.Settings
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainAppLauncher @Inject constructor(
    private val ciceroneHolder: MainCiceroneHolder,
    private val settings: Settings
) {

    private val globalRouter: Router by lazy {
        ciceroneHolder.getRouter(AppFlow.GLOBAL)
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
