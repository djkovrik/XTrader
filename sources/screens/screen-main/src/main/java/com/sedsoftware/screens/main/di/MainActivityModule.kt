package com.sedsoftware.screens.main.di

import androidx.navigation.NavController
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.core.di.holder.NavControllerHolder
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun provideNavController(navControllerHolder: NavControllerHolder): NavController =
        navControllerHolder.getNavController()
}
