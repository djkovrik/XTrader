package com.sedsoftware.screens.splash

import android.os.Bundle
import android.view.View
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.setBackgroundColor

class SplashFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_splash_screen

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundColor(R.attr.colorPrimaryDark)
    }

    override fun inject() {
    }
}
