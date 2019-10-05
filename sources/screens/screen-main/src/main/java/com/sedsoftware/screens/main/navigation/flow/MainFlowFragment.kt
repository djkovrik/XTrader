package com.sedsoftware.screens.main.navigation.flow

import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.screens.main.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainFlowFragment : FlowFragment() {

    companion object {
        fun newInstance(): MainFlowFragment = MainFlowFragment()
    }

    override fun getLaunchScreen(): SupportAppScreen = Screens.Market

    override fun inject() {

    }
}
