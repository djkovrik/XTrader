package com.sedsoftware.main.di

import com.sedsoftware.core.domain.navigation.FlowSwitcher
import com.sedsoftware.core.domain.navigation.StartingFlowCoordinator
import com.sedsoftware.main.flows.navigation.AppFlowSwitcher
import com.sedsoftware.main.flows.navigation.AppStartingFlowCoordinator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
interface NavigationModule {

    @Binds
    @ActivityScoped
    fun bindFlowSwitcher(switcher: AppFlowSwitcher): FlowSwitcher

    @Binds
    @ActivityScoped
    fun bindFlowCoordinator(coordinator: AppStartingFlowCoordinator): StartingFlowCoordinator
}
