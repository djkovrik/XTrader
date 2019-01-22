package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.coordinator.SplashCoordinator
import com.sedsoftware.screens.main.navigation.ActualSplashCoordinator
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideSplashCoordinator(splashCoordinator: ActualSplashCoordinator): SplashCoordinator
}
