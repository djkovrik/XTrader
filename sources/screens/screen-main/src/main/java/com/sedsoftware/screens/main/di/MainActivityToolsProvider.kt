package com.sedsoftware.screens.main.di

import com.sedsoftware.core.presentation.navigation.NavControllerHolder
import com.sedsoftware.screens.main.MainActivity

interface MainActivityToolsProvider {
    fun inject(activity: MainActivity)
    fun provideNavControllerHolder(): NavControllerHolder
}
