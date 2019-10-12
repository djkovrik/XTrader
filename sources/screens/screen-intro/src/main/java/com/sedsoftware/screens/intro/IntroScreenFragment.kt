package com.sedsoftware.screens.intro

import com.sedsoftware.core.presentation.base.BaseFragment

class IntroScreenFragment : BaseFragment() {

    companion object {
        fun newInstance(): IntroScreenFragment = IntroScreenFragment()
    }

    override val layoutResId: Int = R.layout.fragment_intro_screen
}
