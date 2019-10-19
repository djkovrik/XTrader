package com.sedsoftware.screens.wallet

import com.sedsoftware.core.presentation.base.BaseRegularFragment

class WalletScreenFragment : BaseRegularFragment() {

    companion object {
        fun newInstance(): WalletScreenFragment = WalletScreenFragment()
    }

    override val layoutResId: Int = R.layout.fragment_wallet_screen
}
