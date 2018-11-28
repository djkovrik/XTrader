package com.sedsoftware.screens.intro

import android.os.Bundle
import android.view.View
import androidx.navigation.NavDirections
import com.sedsoftware.core.navigation.NavRoutes
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.screens.intro.di.IntroScreenComponent

class IntroScreenFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_intro_screen

    override fun inject() {
        IntroScreenComponent.Initializer.init(parentActivityComponent)
            .inject(this@IntroScreenFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundColor(R.attr.colorPrimaryDark)
    }

    override fun getNavDirectionsFactory(): NavDirectionsFactory {
        return object : NavDirectionsFactory {
            override fun create(route: NavRoutes, arguments: Bundle?): NavDirections {
                return object : NavDirections {
                    override fun getArguments(): Bundle? = null

                    override fun getActionId(): Int = 0
                }
            }
        }
    }
}
