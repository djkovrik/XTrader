package com.sedsoftware.xtrader

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class XTraderApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initTools()
        initDebugTools()
    }

    private fun initTools() {
        AndroidThreeTen.init(this)
    }

    private fun initDebugTools() {
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())
    }
}
