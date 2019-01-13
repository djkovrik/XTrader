package com.sedsoftware.screens.main.factory

import android.os.Bundle
import androidx.navigation.NavOptions
import com.sedsoftware.core.navigation.NavigationRoute
import com.sedsoftware.core.navigation.destination.Destination
import com.sedsoftware.core.navigation.destination.DestinationFactory
import com.sedsoftware.screens.main.R
import javax.inject.Inject

class SplashDestinationsFactory @Inject constructor() : DestinationFactory {

    override fun create(route: NavigationRoute, arguments: Bundle?): Destination =
        when (route) {
            NavigationRoute.Splash.ToIntro ->
                object : Destination {
                    override fun getArguments(): Bundle? =
                        arguments

                    override fun getActionId(): Int =
                        R.id.navigate_from_splash_to_intro

                    override fun getNavOptions(): NavOptions? =
                        NavOptions.Builder()
                            .setPopUpTo(R.id.splashFragment, true)
                            .build()
                }

            NavigationRoute.Splash.ToHome ->
                object : Destination {
                    override fun getArguments(): Bundle? =
                        arguments

                    override fun getActionId(): Int =
                        R.id.navigate_from_splash_to_home

                    override fun getNavOptions(): NavOptions? =
                        NavOptions.Builder()
                            .setPopUpTo(R.id.splashFragment, true)
                            .build()
                }

            NavigationRoute.Splash.ToPin ->
                object : Destination {
                    override fun getArguments(): Bundle? =
                        arguments

                    override fun getActionId(): Int =
                        R.id.navigate_from_splash_to_pin

                    override fun getNavOptions(): NavOptions? =
                        NavOptions.Builder()
                            .setPopUpTo(R.id.splashFragment, true)
                            .build()
                }
        }
}
