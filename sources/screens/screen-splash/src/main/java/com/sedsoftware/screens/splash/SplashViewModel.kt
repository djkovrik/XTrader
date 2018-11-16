package com.sedsoftware.screens.splash

import com.sedsoftware.core.domain.navigation.SplashCoordinator
import com.sedsoftware.core.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val coordinator: SplashCoordinator
) : BaseViewModel() {

    companion object {
        private const val SPLASH_DELAY_MS = 2500L
    }

    fun requestForNextAvailableScreen() {
        launch {
            delay(SPLASH_DELAY_MS)
            coordinator.navigateToNextAvailableScreen()
        }
    }
}
