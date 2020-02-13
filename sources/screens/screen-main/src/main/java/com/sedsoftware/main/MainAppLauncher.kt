package com.sedsoftware.main

import com.sedsoftware.core.tools.api.Settings
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainAppLauncher @Inject constructor(
    private val router: Router, // global
    private val settings: Settings
) {

    fun coldStart() {
        val rootScreen =
            if (settings.isAnyExchangeDownloaded) {
                Screens.RegularFlow
            } else {
                Screens.StartingFlow
            }

        router.newRootScreen(rootScreen)
    }
}
