package com.sedsoftware.core.di

import android.content.Context
import com.sedsoftware.core.di.provider.AppProvider

interface App {
    fun getApplicationContext(): Context
    fun getAppComponent(): AppProvider
}
