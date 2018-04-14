package com.sedsoftware.wexchanger

import android.app.Application
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.di.module.AppModule
import com.sedsoftware.wexchanger.di.module.NetworkingModule
import com.squareup.leakcanary.LeakCanary
import io.realm.Realm
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator
import toothpick.smoothie.module.SmoothieApplicationModule

class WexerApp : Application() {

  override fun onCreate() {
    super.onCreate()

    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }

    LeakCanary.install(this)

    initTimber()
    initToothpick()
    initScopes()
    initRealm()
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
      Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
      FactoryRegistryLocator.setRootRegistry(com.sedsoftware.wexchanger.FactoryRegistry())
      MemberInjectorRegistryLocator.setRootRegistry(com.sedsoftware.wexchanger.MemberInjectorRegistry())
    }

  }

  private fun initScopes() {
    Toothpick
      .openScope(AppScope.APPLICATION)
      .installModules(SmoothieApplicationModule(this),  AppModule(this), NetworkingModule())
  }

  private fun initRealm() {
    Realm.init(this)
  }
}
