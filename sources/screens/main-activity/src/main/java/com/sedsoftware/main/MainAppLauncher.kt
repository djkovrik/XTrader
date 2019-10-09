package com.sedsoftware.main

import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.screens.main.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainAppLauncher @Inject constructor(
    private val settings: Settings,
    private val router: Router
) {

    fun coldStart() {
        val rootScreen =
            if (settings.isExchangesDownloaded) Screens.RegularFlow
            else Screens.StartingFlow

        router.newRootScreen(rootScreen)
    }
}
