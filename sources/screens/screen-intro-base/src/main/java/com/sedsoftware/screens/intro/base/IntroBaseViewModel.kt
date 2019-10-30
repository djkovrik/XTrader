package com.sedsoftware.screens.intro.base

import com.sedsoftware.core.domain.coordinator.StartingFlowCoordinator
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.tools.api.Settings
import javax.inject.Inject

class IntroBaseViewModel @Inject constructor(
    private val coordinator: StartingFlowCoordinator,
    private val settings: Settings
) : BaseViewModel() {

    init {
        if (settings.isCmcDataDownloaded) {
            navigateToExchanges()
        }
    }

    fun navigateToExchanges() {
        coordinator.navigateToExchangeScreen()
    }
}
