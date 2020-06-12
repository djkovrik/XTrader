package com.sedsoftware.screens.wallet

import com.sedsoftware.core.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletScreenFragment : BaseFragment(R.layout.fragment_wallet_screen) {

    companion object {
        fun newInstance(): WalletScreenFragment = WalletScreenFragment()
    }
}
