package com.sedsoftware.main.flows.navigation

import com.sedsoftware.main.flows.AppFlow
import com.sedsoftware.core.tools.api.CiceroneManager
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class GlobalFlowRouter @Inject constructor(
    private val ciceroneManager: CiceroneManager
) {

    private val globalRouter: Router by lazy {
        ciceroneManager.getRouter(AppFlow.GLOBAL)
    }

    fun startFlow(screen: SupportAppScreen) {
        globalRouter.navigateTo(screen)
    }

    fun newRootFlow(screen: SupportAppScreen) {
        globalRouter.newRootScreen(screen)
    }

    fun finishFlow() {
        globalRouter.exit()
    }
}
