package com.sedsoftware.main.tabs

import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.Tabs
import dagger.hilt.android.AndroidEntryPoint
import ru.terrakok.cicerone.android.support.SupportAppScreen

@AndroidEntryPoint
class TrackerTabContainerFragment : BaseTabFragment() {

    companion object {
        fun newInstance(): TrackerTabContainerFragment = TrackerTabContainerFragment()
    }

    override val launchScreen: SupportAppScreen = Screens.Tracker
    override val navigationTag: String = Tabs.TRACKER
}
