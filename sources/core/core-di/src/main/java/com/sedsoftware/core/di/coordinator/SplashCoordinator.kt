package com.sedsoftware.core.di.coordinator

import androidx.navigation.Navigator

interface SplashCoordinator {
    fun navigateToNextScreen(extras: Navigator.Extras)
}
