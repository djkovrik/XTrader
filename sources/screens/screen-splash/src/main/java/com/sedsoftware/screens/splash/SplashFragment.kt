package com.sedsoftware.screens.splash

import android.os.Bundle
import android.view.View
import com.sedsoftware.core.navigation.NavigationRoute
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.destinations
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.screens.splash.di.SplashViewComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashFragment : BaseFragment() {

    @Inject
    lateinit var settings: Settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundColor(R.attr.colorPrimaryDark)

        navigateToNextScreen()
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_splash_screen

    override fun inject() {
        SplashViewComponent.Initializer.init(parentActivityComponent)
            .inject(this@SplashFragment)
    }

    private fun navigateToNextScreen() {
        launch {
            delay(SPLASH_DELAY_MS)
            if (settings.isExchangesDownloaded) {
                router.navigateTo(destinations.create(NavigationRoute.Splash.ToHome))
            } else {
                router.navigateTo(destinations.create(NavigationRoute.Splash.ToIntro))
            }
        }
    }

    private companion object {
        const val SPLASH_DELAY_MS = 3000L
    }
}
