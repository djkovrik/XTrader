package com.sedsoftware.screens.main.navigation.flow

import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.screens.main.navigation.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen

class IntroFlowFragment : FlowFragment() {

    companion object {
        fun newInstance(): IntroFlowFragment = IntroFlowFragment()
    }

    override fun getLaunchScreen(): SupportAppScreen = Screens.Intro
}
