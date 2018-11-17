package com.sedsoftware.xtrader

import android.app.Application
import com.facebook.stetho.Stetho
import com.frogermcs.androiddevmetrics.AndroidDevMetrics
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.xtrader.di.AppComponent
import timber.log.Timber

class XTraderApp : Application(), App {

    private val appComponent: AppComponent by lazy { AppComponent.Initializer.init(this@XTraderApp) }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)

        initStetho()
        initTimber()
        initDevMetrics()
    }

    override fun getAppComponent(): AppProvider = appComponent

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initTimber() {
        Timber.uprootAll()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initDevMetrics() {
        if (BuildConfig.DEBUG) {
            AndroidDevMetrics.initWith(this)
        }
    }
}
