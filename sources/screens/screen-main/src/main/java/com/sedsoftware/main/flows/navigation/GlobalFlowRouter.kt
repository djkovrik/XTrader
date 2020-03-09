package com.sedsoftware.main.flows.navigation

import com.sedsoftware.core.presentation.navigation.AppFlow
import com.sedsoftware.core.presentation.navigation.MainCiceroneHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class GlobalFlowRouter @Inject constructor(
    private val ciceroneHolder: MainCiceroneHolder
) {

    private val globalRouter: Router by lazy {
        ciceroneHolder.getRouter(AppFlow.GLOBAL)
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
