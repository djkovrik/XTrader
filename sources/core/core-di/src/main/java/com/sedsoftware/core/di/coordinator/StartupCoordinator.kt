package com.sedsoftware.core.di.coordinator

import androidx.lifecycle.LifecycleObserver

interface StartupCoordinator : LifecycleObserver {
    fun navigateToNextScreen()
}
