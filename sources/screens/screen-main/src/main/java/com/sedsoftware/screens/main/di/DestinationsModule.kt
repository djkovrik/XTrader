package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.key.DestinationFactoryKey
import com.sedsoftware.core.navigation.destination.DestinationFactory
import com.sedsoftware.screens.main.factory.SplashDestinationsFactory
import com.sedsoftware.screens.splash.SplashFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DestinationsModule {

    @Binds
    @IntoMap
    @DestinationFactoryKey(SplashFragment::class)
    abstract fun provideSplashDestinations(factory: SplashDestinationsFactory): DestinationFactory
}
