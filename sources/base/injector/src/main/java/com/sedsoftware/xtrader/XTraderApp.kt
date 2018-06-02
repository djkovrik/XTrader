package com.sedsoftware.xtrader

import android.app.Application
import com.sedsoftware.core.App
import com.sedsoftware.core.di.provider.ApplicationProvider
import com.sedsoftware.xtrader.di.AppComponent

class XTraderApp : Application(), App {

  private val appComponent: AppComponent by lazy { AppComponent.Initializer.init(this@XTraderApp) }

  override fun onCreate() {
    super.onCreate()
    appComponent.inject(this)
  }

  override fun getAppComponent(): ApplicationProvider = appComponent
}
