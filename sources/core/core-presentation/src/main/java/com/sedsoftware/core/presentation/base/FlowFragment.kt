package com.sedsoftware.core.presentation.base

import android.os.Bundle
import com.sedsoftware.core.presentation.extension.setLaunchScreen
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen

abstract class FlowFragment : BaseFragment() {

    abstract val launchScreen: SupportAppScreen
    abstract val navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            navigator.setLaunchScreen(launchScreen)
        }
    }
}
