package com.sedsoftware.main.tabs

import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.core.di.management.HasInject
import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.Tabs
import com.sedsoftware.main.di.MainActivityComponent
import ru.terrakok.cicerone.android.support.SupportAppScreen

class TrackerTabContainerFragment : BaseTabFragment(), HasInject {

    companion object {
        fun newInstance(): TrackerTabContainerFragment = TrackerTabContainerFragment()
    }

    override val launchScreen: SupportAppScreen = Screens.Tracker
    override val navigationTag: String = Tabs.TRACKER

    override fun inject() {
        DaggerComponentManager
            .get<MainActivityComponent>()
            .inject(this)
    }
}
