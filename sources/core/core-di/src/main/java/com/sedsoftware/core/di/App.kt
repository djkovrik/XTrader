package com.sedsoftware.core.di

import android.content.Context

interface App {
    fun getApplicationContext(): Context
    fun getAppComponent(): AppProvider
}
