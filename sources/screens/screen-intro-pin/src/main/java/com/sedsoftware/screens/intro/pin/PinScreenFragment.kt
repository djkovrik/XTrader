package com.sedsoftware.screens.intro.pin

import com.sedsoftware.core.presentation.base.BaseStartingFragment

class PinScreenFragment : BaseStartingFragment() {

    companion object {
        fun newInstance(): PinScreenFragment = PinScreenFragment()
    }

    override val layoutResId: Int = R.layout.fragment_pin_screen
}
