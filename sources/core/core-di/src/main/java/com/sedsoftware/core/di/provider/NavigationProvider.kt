package com.sedsoftware.core.di.provider

import com.sedsoftware.core.navigation.NavControllerHolder

interface NavigationProvider {
    fun provideNavControllerHolder(): NavControllerHolder
}
