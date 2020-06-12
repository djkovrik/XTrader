package com.sedsoftware.main.tabs

import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.Tabs
import dagger.hilt.android.AndroidEntryPoint
import ru.terrakok.cicerone.android.support.SupportAppScreen

@AndroidEntryPoint
class MarketTabContainerFragment : BaseTabFragment() {

    companion object {
        fun newInstance(): MarketTabContainerFragment = MarketTabContainerFragment()
    }

    override val launchScreen: SupportAppScreen = Screens.Market
    override val navigationTag: String = Tabs.MARKET
}
