package com.sedsoftware.screens.main.factory

import android.os.Bundle
import androidx.navigation.NavDirections
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.core.presentation.navigation.NavDirectionsFactory
import com.sedsoftware.core.presentation.navigation.NavRoutes
import com.sedsoftware.screens.main.R
import javax.inject.Inject

@ActivityScope
class SplashNavDirectionsFactory @Inject constructor() : NavDirectionsFactory {

    override fun create(route: NavRoutes, arguments: Bundle?): NavDirections =
        when (route) {
            NavRoutes.Splash.ToIntro ->
                object : NavDirections {
                    override fun getArguments(): Bundle? = arguments
                    override fun getActionId(): Int = R.id.navigate_from_splash_to_intro
                }
            NavRoutes.Splash.ToHome ->
                object : NavDirections {
                    override fun getArguments(): Bundle? = arguments
                    override fun getActionId(): Int = R.id.navigate_from_splash_to_home
                }
            NavRoutes.Splash.ToPin ->
                object : NavDirections {
                    override fun getArguments(): Bundle? = arguments
                    override fun getActionId(): Int = R.id.navigate_from_splash_to_pin
                }
        }
}
