package com.sedsoftware.main.di.module

import com.sedsoftware.core.domain.coordinator.FlowSwitcher
import com.sedsoftware.core.domain.coordinator.StartingFlowCoordinator
import com.sedsoftware.main.flows.navigation.AppFlowSwitcher
import com.sedsoftware.main.flows.navigation.AppStartingFlowCoordinator
import dagger.Binds
import dagger.Module

@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindFlowSwitcher(switcher: AppFlowSwitcher): FlowSwitcher

    @Binds
    abstract fun bindFlowCoordinator(coordinator: AppStartingFlowCoordinator): StartingFlowCoordinator
}
