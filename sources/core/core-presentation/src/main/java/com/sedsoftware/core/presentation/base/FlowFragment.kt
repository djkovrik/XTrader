package com.sedsoftware.core.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.sedsoftware.core.presentation.extension.setLaunchScreen
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen

abstract class FlowFragment(@LayoutRes layoutResId: Int) : BaseFragment(layoutResId) {

    abstract val launchScreen: SupportAppScreen
    abstract val navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            navigator.setLaunchScreen(launchScreen)
        }
    }
}
