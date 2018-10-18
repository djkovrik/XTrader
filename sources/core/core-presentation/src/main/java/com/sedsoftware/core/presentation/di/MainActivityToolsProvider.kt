package com.sedsoftware.core.presentation.di

import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.core.presentation.navigation.NavControllerHolder

interface MainActivityToolsProvider {
    fun provideNavControllerHolder(): NavControllerHolder

    fun inject(activity: BaseActivity)
}
