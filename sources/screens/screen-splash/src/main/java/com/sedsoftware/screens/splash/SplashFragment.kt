package com.sedsoftware.screens.splash

import android.os.Bundle
import android.view.View
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.navDirectionsFactory
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.screens.splash.di.SplashViewComponent

class SplashFragment : BaseFragment() {

    private lateinit var splashViewModel: SplashViewModel

    override fun getLayoutId(): Int = R.layout.fragment_splash_screen

    override fun inject() {
        SplashViewComponent.Initializer.init(parentActivityComponent, this)
            .inject(this@SplashFragment)
    }

    override fun getNavDirectionsFactory(): NavDirectionsFactory =
        navDirectionsFactory(directionsFactoryMap, SplashFragment::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel = viewModel(viewModelFactory) {
            failure(failure, ::handleSplashFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundColor(R.attr.colorPrimaryDark)
    }

    private fun handleSplashFailure(failure: Failure?) {

    }
}
