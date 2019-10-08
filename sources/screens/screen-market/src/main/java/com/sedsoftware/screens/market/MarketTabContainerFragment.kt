package com.sedsoftware.screens.market

import com.sedsoftware.core.presentation.base.BaseFragment

class MarketTabContainerFragment : BaseFragment() {

    companion object {
        fun newInstance(): MarketTabContainerFragment = MarketTabContainerFragment()
    }

    override val layoutResId: Int = R.layout.layout_container
}
