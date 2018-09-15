package com.sedsoftware.coreapi

import android.content.Context
import com.sedsoftware.coreapi.di.provider.ApplicationProvider

interface App {
    fun getApplicationContext(): Context
    fun getAppComponent(): ApplicationProvider
}
