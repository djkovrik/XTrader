package com.sedsoftware.screens.splash.di

import com.sedsoftware.core.navigation.coordinator.SplashCoordinator
import com.sedsoftware.screens.splash.navigation.RealSplashCoordinator
import dagger.Binds
import dagger.Module

@Module
abstract class SplashViewModule {

    @Binds
    abstract fun bindSplashCoordinator(splashCoordinator: RealSplashCoordinator): SplashCoordinator
}
