package com.sedsoftware.main.di.module

import com.sedsoftware.core.domain.navigation.FlowSwitcher
import com.sedsoftware.core.domain.navigation.StartingFlowCoordinator
import com.sedsoftware.main.flows.navigation.AppFlowSwitcher
import com.sedsoftware.main.flows.navigation.AppStartingFlowCoordinator
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @Binds
    fun bindFlowSwitcher(switcher: AppFlowSwitcher): FlowSwitcher

    @Binds
    fun bindFlowCoordinator(coordinator: AppStartingFlowCoordinator): StartingFlowCoordinator
}
