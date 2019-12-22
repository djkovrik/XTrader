package com.sedsoftware.main.tabs

import android.content.Context
import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.flows.regular.di.RegularFlowComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.terrakok.cicerone.android.support.SupportAppScreen

class WalletTabContainerFragment : BaseTabFragment() {

    companion object {
        fun newInstance(): WalletTabContainerFragment =
            WalletTabContainerFragment()
    }

    override val launchScreen: SupportAppScreen = Screens.Wallet

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = XInjectionManager
            .findComponent { it is RegularFlowComponent } as RegularFlowComponent

        component.inject(this)
    }
}
