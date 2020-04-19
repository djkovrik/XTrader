package com.sedsoftware.main.tabs

import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.main.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen

class OrdersTabContainerFragment : BaseTabFragment() {

    companion object {
        fun newInstance(): OrdersTabContainerFragment =
            OrdersTabContainerFragment()
    }

    override val launchScreen: SupportAppScreen = Screens.Empty
}
