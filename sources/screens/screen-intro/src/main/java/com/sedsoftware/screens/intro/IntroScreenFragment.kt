package com.sedsoftware.screens.intro

import android.os.Bundle
import android.view.View
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.custom.CustomTransition
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.screens.intro.di.IntroScreenComponent

class IntroScreenFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundColor(R.attr.colorPrimaryDark)
    }

    override fun getSharedElementEnterTransition(): Any? {
        return CustomTransition()
    }

    override fun getSharedElementReturnTransition(): Any? {
        return CustomTransition()
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_intro_screen

    override fun inject() {
        IntroScreenComponent.Initializer.init(parentActivityComponent)
            .inject(this@IntroScreenFragment)
    }
}
