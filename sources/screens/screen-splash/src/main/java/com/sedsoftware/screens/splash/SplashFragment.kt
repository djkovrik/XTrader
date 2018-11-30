package com.sedsoftware.screens.splash

import android.os.Bundle
import android.view.View
import com.sedsoftware.core.navigation.coordinator.SplashCoordinator
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import com.sedsoftware.core.navigation.holder.NavControllerHolder
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.navDirectionsFactory
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.screens.splash.di.SplashViewComponent
import javax.inject.Inject

class SplashFragment : BaseFragment() {

    private lateinit var splashViewModel: SplashViewModel

    override val layoutResId: Int
        get() = R.layout.fragment_splash_screen

    @Inject
    lateinit var coordinator: SplashCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel = viewModel(viewModelFactory) {
            failure(failure, ::handleSplashFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundColor(R.attr.colorPrimaryDark)

        coordinator.start()
    }

    override fun inject() {
        SplashViewComponent.Initializer.init(parentActivityComponent, activity as NavControllerHolder, this)
            .inject(this@SplashFragment)
    }

    override fun getNavDirectionsFactory(): NavDirectionsFactory =
        navDirectionsFactory(directionsFactoryMap, SplashFragment::class.java)

    private fun handleSplashFailure(failure: Failure?) {

    }
}
