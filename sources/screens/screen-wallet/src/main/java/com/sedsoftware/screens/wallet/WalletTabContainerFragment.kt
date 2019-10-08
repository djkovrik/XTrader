package com.sedsoftware.screens.wallet

import com.sedsoftware.core.presentation.base.BaseFragment

class WalletTabContainerFragment : BaseFragment() {

    companion object {
        fun newInstance(): WalletTabContainerFragment = WalletTabContainerFragment()
    }

    override val layoutResId: Int = R.layout.layout_container
}
