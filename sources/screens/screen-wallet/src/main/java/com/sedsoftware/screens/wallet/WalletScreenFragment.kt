package com.sedsoftware.screens.wallet

import com.sedsoftware.core.presentation.base.BaseFragment

class WalletScreenFragment : BaseFragment() {

    companion object {

        fun newInstance(): WalletScreenFragment = WalletScreenFragment()
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_wallet_screen
}
