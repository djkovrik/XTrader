package com.sedsoftware.core.domain.coordinator

interface FlowCoordinator {
    fun switchToStartingFlow()
    fun switchToRegularFlow()
}
