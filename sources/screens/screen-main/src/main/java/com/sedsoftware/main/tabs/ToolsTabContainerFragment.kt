package com.sedsoftware.main.tabs

import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.main.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ToolsTabContainerFragment : BaseTabFragment() {

    companion object {
        fun newInstance(): ToolsTabContainerFragment =
            ToolsTabContainerFragment()
    }

    override val launchScreen: SupportAppScreen = Screens.Tools
}
