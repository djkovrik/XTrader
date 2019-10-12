package com.sedsoftware.screens.market

import com.sedsoftware.core.presentation.base.BaseFragment


class MarketScreenFragment : BaseFragment() {

    companion object {
        fun newInstance(): MarketScreenFragment = MarketScreenFragment()
    }

    override val layoutResId: Int = R.layout.fragment_market_screen
}
