package com.sedsoftware.core.domain.coordinator

interface FlowSwitcher {
    fun switchToStartingFlow()
    fun switchToRegularFlow()
}
