package com.sedsoftware.core.di

import android.content.Context
import com.sedsoftware.core.di.provider.ApplicationProvider

interface App {
    fun getApplicationContext(): Context
    fun getAppComponent(): ApplicationProvider
}
