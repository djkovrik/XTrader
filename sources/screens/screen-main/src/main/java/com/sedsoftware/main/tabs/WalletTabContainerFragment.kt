package com.sedsoftware.main.tabs

import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.core.di.management.HasInject
import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.di.MainActivityComponent
import ru.terrakok.cicerone.android.support.SupportAppScreen

class WalletTabContainerFragment : BaseTabFragment(), HasInject {

    companion object {
        fun newInstance(): WalletTabContainerFragment = WalletTabContainerFragment()
    }

    override val launchScreen: SupportAppScreen = Screens.Wallet

    override fun inject() {
        DaggerComponentManager
            .get<MainActivityComponent>()
            .inject(this)
    }
}
