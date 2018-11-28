package com.sedsoftware.screens.pin

import android.os.Bundle
import androidx.navigation.NavDirections
import com.sedsoftware.core.navigation.NavRoutes
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import com.sedsoftware.core.presentation.base.BaseFragment

class PinScreenFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_pin_screen

    override fun inject() {
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
