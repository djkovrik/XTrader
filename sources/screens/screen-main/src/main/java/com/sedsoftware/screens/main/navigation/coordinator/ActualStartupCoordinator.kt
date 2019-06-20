package com.sedsoftware.screens.main.navigation.coordinator

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.sedsoftware.core.di.coordinator.StartupCoordinator
import com.sedsoftware.core.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class ActualStartupCoordinator @Inject constructor() : StartupCoordinator {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun navigateToNextScreen() {

    }
}
