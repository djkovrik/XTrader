package com.sedsoftware.main.tabs


import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.Tabs
import dagger.hilt.android.AndroidEntryPoint
import ru.terrakok.cicerone.android.support.SupportAppScreen

@AndroidEntryPoint
class OrdersTabContainerFragment : BaseTabFragment() {

    companion object {
        fun newInstance(): OrdersTabContainerFragment = OrdersTabContainerFragment()
    }

    override val launchScreen: SupportAppScreen = Screens.Orders
    override val navigationTag: String = Tabs.ORDERS
}
