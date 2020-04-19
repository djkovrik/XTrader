package com.sedsoftware.xtrader

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.xtrader.di.AppComponent
import timber.log.Timber

class XTraderApp : Application(), App {

    private val appComponent: AppComponent by lazy {
        AppComponent.Initializer.init(this@XTraderApp)
    }

    override fun onCreate() {
        super.onCreate()

        DaggerComponentManager.init(this)

        appComponent.inject(this)

        initTools()

        if (BuildConfig.DEBUG) {
            initDebugTools()
        }
    }

    override fun getAppComponent(): AppProvider = appComponent

    private fun initTools() {
        AndroidThreeTen.init(this)
    }

    private fun initDebugTools() {
//        AndroidDevMetrics.initWith(this)
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())
    }
}
