package com.sedsoftware.screens.intro

import com.sedsoftware.core.domain.coordinator.FlowCoordinator
import com.sedsoftware.core.presentation.base.BaseViewModel
import javax.inject.Inject

class IntroScreenViewModel @Inject constructor(
    private val coordinator: FlowCoordinator
) : BaseViewModel() {

    fun switchToRegularFlow() {
        coordinator.switchToRegularFlow()
    }
}
