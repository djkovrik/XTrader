package com.sedsoftware.main

import androidx.fragment.app.Fragment
import com.sedsoftware.main.flows.regular.RegularFlowFragment
import com.sedsoftware.main.flows.starting.StartingFlowFragment
import com.sedsoftware.screens.intro.exchanges.IntroExchangesFragment
import com.sedsoftware.screens.market.MarketScreenFragment
import com.sedsoftware.screens.market.MarketTabContainerFragment
import com.sedsoftware.screens.orders.OrdersScreenFragment
import com.sedsoftware.screens.orders.OrdersTabContainerFragment
import com.sedsoftware.screens.intro.pin.PinScreenFragment
import com.sedsoftware.screens.tools.ToolsScreenFragment
import com.sedsoftware.screens.tools.ToolsTabContainerFragment
import com.sedsoftware.screens.tracker.TrackerScreenFragment
import com.sedsoftware.screens.tracker.TrackerTabContainerFragment
import com.sedsoftware.screens.wallet.WalletScreenFragment
import com.sedsoftware.screens.wallet.WalletTabContainerFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    // Flows
    object StartingFlow : SupportAppScreen() {
        override fun getFragment(): Fragment = StartingFlowFragment.newInstance()
    }

    object RegularFlow : SupportAppScreen() {
        override fun getFragment(): Fragment = RegularFlowFragment.newInstance()
    }


    // Containers
    object MarketTabContainer : SupportAppScreen() {
        override fun getFragment(): Fragment = MarketTabContainerFragment.newInstance()
    }

    object WalletTabContainer : SupportAppScreen() {
        override fun getFragment(): Fragment = WalletTabContainerFragment.newInstance()
    }

    object OrdersTabContainer : SupportAppScreen() {
        override fun getFragment(): Fragment = OrdersTabContainerFragment.newInstance()
    }

    object TrackerTabContainer : SupportAppScreen() {
        override fun getFragment(): Fragment = TrackerTabContainerFragment.newInstance()
    }

    object ToolsTabContainer : SupportAppScreen() {
        override fun getFragment(): Fragment = ToolsTabContainerFragment.newInstance()
    }


    // Screens
    object Intro : SupportAppScreen() {
        override fun getFragment(): Fragment = IntroExchangesFragment.newInstance()
    }

    object Pin : SupportAppScreen() {
        override fun getFragment(): Fragment = PinScreenFragment.newInstance()
    }

    object Market : SupportAppScreen() {
        override fun getFragment(): Fragment = MarketScreenFragment.newInstance()
    }

    object Orders : SupportAppScreen() {
        override fun getFragment(): Fragment = OrdersScreenFragment.newInstance()
    }

    object Wallet : SupportAppScreen() {
        override fun getFragment(): Fragment = WalletScreenFragment.newInstance()
    }

    object Tracker : SupportAppScreen() {
        override fun getFragment(): Fragment = TrackerScreenFragment.newInstance()
    }

    object Tools : SupportAppScreen() {
        override fun getFragment(): Fragment = ToolsScreenFragment.newInstance()
    }
}
