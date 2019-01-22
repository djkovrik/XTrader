package com.sedsoftware.screens.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.sedsoftware.core.di.coordinator.SplashCoordinator
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.core.presentation.extension.string
import com.sedsoftware.screens.splash.di.SplashViewComponent
import kotlinx.android.synthetic.main.fragment_splash_screen.logo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashFragment : BaseFragment() {

    @Inject
    lateinit var coordinator: SplashCoordinator

    private val navExtras: Navigator.Extras by lazy {
        FragmentNavigatorExtras(
            logo to string(R.string.transition_logo)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundColor(R.attr.colorPrimaryDark)

        launch {
            delay(SPLASH_DELAY_MS)
            coordinator.navigateToNextScreen(navExtras)
        }
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_splash_screen

    override fun inject() {
        SplashViewComponent.Initializer.init(parentActivityComponent)
            .inject(this@SplashFragment)
    }

    private companion object {
        const val SPLASH_DELAY_MS = 2500L
    }
}
