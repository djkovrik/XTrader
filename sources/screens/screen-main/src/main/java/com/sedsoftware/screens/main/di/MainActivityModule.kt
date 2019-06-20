package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.di.coordinator.StartupCoordinator
import com.sedsoftware.screens.main.navigation.coordinator.ActualIntroCoordinator
import com.sedsoftware.screens.main.navigation.coordinator.ActualStartupCoordinator
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideSplashCoordinator(splashCoordinator: ActualStartupCoordinator): StartupCoordinator

    @Binds
    abstract fun provideIntroCoordinator(introCoordinator: ActualIntroCoordinator): IntroCoordinator
}
