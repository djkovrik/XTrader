package com.sedsoftware.xtrader

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.xtrader.di.AppComponent

class XTraderApp : Application(), App {

    private val appComponent: AppComponent by lazy {
        AppComponent.Initializer.init(this@XTraderApp)
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        appComponent.inject(this)
    }

    override fun getAppComponent(): AppProvider =
        appComponent
}
