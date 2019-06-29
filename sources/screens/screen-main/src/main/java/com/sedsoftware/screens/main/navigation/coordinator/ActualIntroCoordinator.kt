package com.sedsoftware.screens.main.navigation.coordinator

import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.di.delegate.NavigationFlowDelegate
import com.sedsoftware.core.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class ActualIntroCoordinator @Inject constructor(
    private val navigationFlowDelegate: NavigationFlowDelegate
) : IntroCoordinator {

    override fun navigateToHome() {
        navigationFlowDelegate.switchToMainFlow()
    }
}
