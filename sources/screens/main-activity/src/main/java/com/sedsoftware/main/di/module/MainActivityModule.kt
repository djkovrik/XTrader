package com.sedsoftware.main.di.module

import com.sedsoftware.core.domain.coordinator.FlowCoordinator
import com.sedsoftware.main.flows.coordinator.AppFlowCoordinator
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindFlowCoordinator(coordinator: AppFlowCoordinator): FlowCoordinator
}
