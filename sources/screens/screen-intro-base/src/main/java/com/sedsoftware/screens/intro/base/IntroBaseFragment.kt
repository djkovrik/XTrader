package com.sedsoftware.screens.intro.base

import com.sedsoftware.core.presentation.base.BaseRegularFragment

class IntroBaseFragment : BaseRegularFragment() {

    companion object {
        fun newInstance(): IntroBaseFragment = IntroBaseFragment()
    }

    override val layoutResId: Int = R.layout.fragment_intro_base
}
