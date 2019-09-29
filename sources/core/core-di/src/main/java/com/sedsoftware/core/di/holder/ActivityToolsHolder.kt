package com.sedsoftware.core.di.holder

import com.sedsoftware.core.di.MainActivityToolsProvider

interface ActivityToolsHolder {
    fun getActivityToolsProvider(): MainActivityToolsProvider
}
