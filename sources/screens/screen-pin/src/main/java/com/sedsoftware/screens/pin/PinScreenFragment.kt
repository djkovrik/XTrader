package com.sedsoftware.screens.pin

import com.sedsoftware.core.presentation.base.BaseFragment

class PinScreenFragment : BaseFragment() {

    companion object {

        fun newInstance(): PinScreenFragment = PinScreenFragment()
    }

    override val layoutResId: Int = R.layout.fragment_pin_screen
}
