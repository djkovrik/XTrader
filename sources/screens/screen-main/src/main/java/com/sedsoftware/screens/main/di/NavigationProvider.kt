package com.sedsoftware.screens.main.di

import com.sedsoftware.screens.main.navigation.NavControllerHolder
import com.sedsoftware.screens.main.navigation.Router

interface NavigationProvider {
    fun provideNavControllerHolder(): NavControllerHolder
    fun provideRouter(): Router
}
