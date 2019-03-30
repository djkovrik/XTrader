package com.sedsoftware.xtrader

import android.app.Application
import com.facebook.stetho.Stetho
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.xtrader.di.AppComponent
import timber.log.Timber

class XTraderApp : Application(), App {

    private val appComponent: AppComponent by lazy {
        AppComponent.Initializer.init(this@XTraderApp)
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)

        initDebugTools()
    }

    override fun getAppComponent(): AppProvider =
        appComponent

    private fun initDebugTools() {
        Timber.uprootAll()

        if (BuildConfig.DEBUG) {
//            AndroidDevMetrics.initWith(this)
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
    }
}
