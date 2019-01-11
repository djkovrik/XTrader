package com.sedsoftware.core.di.holder

import com.sedsoftware.core.di.provider.MainActivityToolsProvider

interface ActivityToolsHolder {
    fun getActivityToolsProvider(): MainActivityToolsProvider
}
