package com.sedsoftware.screens.splash

import com.sedsoftware.core.domain.navigation.SplashCoordinator
import com.sedsoftware.core.presentation.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val coordinator: SplashCoordinator
) : BaseViewModel() {

    fun requestForNextAvailableScreen() {
        coordinator.navigateToNextAvailableScreen()
    }
}
