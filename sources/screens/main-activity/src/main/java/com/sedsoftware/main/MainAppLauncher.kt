package com.sedsoftware.main

import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.screens.main.navigation.Screens.StartingFlow
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainAppLauncher @Inject constructor(
    private val settings: Settings,
    private val router: Router
) {

    fun coldStart() {
        val rootScreen = StartingFlow
        router.newRootScreen(rootScreen)
    }
}
