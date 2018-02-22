package com.sedsoftware.wexchanger

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration

class WexerApp : Application() {

  override fun onCreate() {
    super.onCreate()

    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }

    LeakCanary.install(this)

    initTimber()
    initToothpick()
  }

  @Suppress("ConstantConditionIf")
  private fun initTimber() {
    Timber.uprootAll()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }


  private fun initToothpick() {
    if (BuildConfig.DEBUG) {
      Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
    } else {
      Toothpick.setConfiguration(Configuration.forProduction())
    }
  }
}
