package com.sedsoftware.screens.intro

import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.intro.di.IntroScreenComponent

class IntroScreenFragment : BaseFragment() {

    override fun getLayoutResId(): Int =
        R.layout.fragment_intro_screen

    override fun inject() {
        IntroScreenComponent.Initializer.init(parentActivityComponent)
            .inject(this@IntroScreenFragment)
    }
}
