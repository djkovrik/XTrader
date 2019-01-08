package com.sedsoftware.screens.splash

import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.navigation.destination.DestinationFactory
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.presentation.extension.getDestinations
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val router: Router
) : BaseViewModel() {

    private val destinations: DestinationFactory by lazy {
        getDestinations(SplashFragment::class.java)
    }
}
