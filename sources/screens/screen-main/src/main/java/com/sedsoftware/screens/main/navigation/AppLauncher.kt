package com.sedsoftware.screens.main.navigation

import com.sedsoftware.core.tools.api.Settings
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppLauncher @Inject constructor(
    private val settings: Settings,
    private val router: Router
) {

    fun coldStart() {
        val rootScreen = Screens.IntroFlow
//            if (settings.isExchangesDownloaded) Screens.MainFlow else Screens.IntroFlow

        router.newRootScreen(rootScreen)
    }
}
