package com.sedsoftware.screens.intro

import com.sedsoftware.core.presentation.base.BaseFragment

class IntroTabContainerFragment : BaseFragment() {

    companion object {
        fun newInstance(): IntroTabContainerFragment = IntroTabContainerFragment()
    }

    override val layoutResId: Int = R.layout.layout_container
}
