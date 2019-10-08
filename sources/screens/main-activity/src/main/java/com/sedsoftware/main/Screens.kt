package com.sedsoftware.screens.main.navigation

import androidx.fragment.app.Fragment
import com.sedsoftware.main.flows.regular.RegularFlowFragment
import com.sedsoftware.main.flows.starting.StartingFlowFragment
import com.sedsoftware.screens.intro.IntroScreenFragment
import com.sedsoftware.screens.market.MarketScreenFragment
import com.sedsoftware.screens.orders.OrdersScreenFragment
import com.sedsoftware.screens.tools.ToolsScreenFragment
import com.sedsoftware.screens.tracker.TrackerScreenFragment
import com.sedsoftware.screens.wallet.WalletScreenFragment
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

    // Screens
    object Intro : SupportAppScreen() {
        override fun getFragment(): Fragment = IntroScreenFragment.newInstance()
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
