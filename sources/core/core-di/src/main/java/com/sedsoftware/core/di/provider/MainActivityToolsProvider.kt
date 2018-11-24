package com.sedsoftware.core.di.provider

import com.sedsoftware.core.di.holder.ActivityComponentHolder

interface MainActivityToolsProvider : ViewModelFactoryProvider {
    fun inject(activity: ActivityComponentHolder)
}
