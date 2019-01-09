package com.sedsoftware.core.di.provider

import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.navigation.Router

interface NavigationProvider {
    fun provideRouter(): Router
    fun provideNavControllerHolder(): NavControllerHolder
}
