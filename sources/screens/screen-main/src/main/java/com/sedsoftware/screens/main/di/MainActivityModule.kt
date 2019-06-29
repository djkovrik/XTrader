package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.screens.main.navigation.coordinator.ActualIntroCoordinator
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideIntroCoordinator(introCoordinator: ActualIntroCoordinator): IntroCoordinator
}
