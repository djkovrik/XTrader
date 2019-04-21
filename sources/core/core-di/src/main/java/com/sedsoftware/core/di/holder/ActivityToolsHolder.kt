package com.sedsoftware.core.di.holder

import android.view.ViewGroup
import com.sedsoftware.core.di.provider.MainActivityToolsProvider

interface ActivityToolsHolder {
    fun getActivityToolsProvider(): MainActivityToolsProvider
    fun getRootContainer(): ViewGroup
}
