package com.sedsoftware.main.tabs

import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.Tabs
import dagger.hilt.android.AndroidEntryPoint
import ru.terrakok.cicerone.android.support.SupportAppScreen

@AndroidEntryPoint
class WalletTabContainerFragment : BaseTabFragment() {

    companion object {
        fun newInstance(): WalletTabContainerFragment = WalletTabContainerFragment()
    }

    override val launchScreen: SupportAppScreen = Screens.Wallet
    override val navigationTag: String = Tabs.WALLET
}
