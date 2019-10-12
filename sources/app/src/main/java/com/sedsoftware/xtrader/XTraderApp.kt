package com.sedsoftware.xtrader

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.xtrader.di.AppComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import timber.log.Timber

class XTraderApp : Application(), App {

    private val appComponent: AppComponent by lazy {
        AppComponent.Initializer.init(this@XTraderApp)
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        XInjectionManager.init(this)
        appComponent.inject(this)

        initDebugTools()
    }

    override fun getAppComponent(): AppProvider =
        appComponent

    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
//        AndroidDevMetrics.initWith(this)
            Timber.uprootAll()
            Timber.plant(Timber.DebugTree())
        }
    }
}
