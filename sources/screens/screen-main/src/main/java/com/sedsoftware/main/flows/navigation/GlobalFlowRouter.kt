package com.sedsoftware.main.flows.navigation

import com.sedsoftware.core.domain.tools.CiceroneManager
import com.sedsoftware.main.flows.AppFlow
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class GlobalFlowRouter @Inject constructor(
    private val ciceroneManager: CiceroneManager
) {

    private val globalRouter: Router by lazy {
        ciceroneManager.getRouter(AppFlow.GLOBAL)
    }

    fun newRootFlow(screen: SupportAppScreen) {
        globalRouter.newRootScreen(screen)
    }
}
