package com.sedsoftware.screens.main.di

import com.sedsoftware.core.domain.navigation.SplashCoordinator
import com.sedsoftware.screens.main.navigation.SplashScreenCoordinator
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideSplashCoordinator(coordinator: SplashScreenCoordinator): SplashCoordinator = coordinator
}
