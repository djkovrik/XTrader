package com.sedsoftware.xtrader

import android.app.Application
import com.facebook.stetho.Stetho
import com.sedsoftware.coredi.App
import com.sedsoftware.coredi.provider.ApplicationProvider
import com.sedsoftware.xtrader.di.AppComponent
import timber.log.Timber

class XTraderApp : Application(), App {

    private val appComponent: AppComponent by lazy { AppComponent.Initializer.init(this@XTraderApp) }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)

        initStetho()
        initTimber()
    }

    override fun getAppComponent(): ApplicationProvider = appComponent

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
}
