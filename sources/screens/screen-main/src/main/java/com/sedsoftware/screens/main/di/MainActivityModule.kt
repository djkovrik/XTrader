package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.key.NavDirectionsFactoryKey
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import com.sedsoftware.screens.main.factory.SplashNavDirectionsFactory
import com.sedsoftware.screens.splash.SplashFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @NavDirectionsFactoryKey(SplashFragment::class)
    abstract fun provideSplashNavDirectionsFactory(factory: SplashNavDirectionsFactory): NavDirectionsFactory
}
