package com.sedsoftware.coredi

import android.content.Context
import com.sedsoftware.coredi.provider.ApplicationProvider

interface App {
    fun getApplicationContext(): Context
    fun getAppComponent(): ApplicationProvider
}
