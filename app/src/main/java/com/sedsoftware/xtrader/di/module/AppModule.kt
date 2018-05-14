package com.sedsoftware.xtrader.di.module

import android.app.Application
import android.content.Context
import com.sedsoftware.data.executor.ThreadExecutor
import com.sedsoftware.domain.device.Settings
import com.sedsoftware.domain.executor.Executor
import com.sedsoftware.xtrader.di.provider.SettingsProvider
import com.sedsoftware.xtrader.presentation.navigation.LocalNavigatorHolder
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class AppModule(application: Application) : Module() {
  init {
    // Context
    bind(Context::class.java).toInstance(application)

    //Navigation
    val cicerone = Cicerone.create()
    bind(Router::class.java).toInstance(cicerone.router)
    bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    bind(LocalNavigatorHolder::class.java).toInstance(LocalNavigatorHolder())

    // Threading
    bind(Executor::class.java).toInstance(ThreadExecutor())

    // Settings
    bind(Settings::class.java).toProvider(SettingsProvider::class.java).providesSingletonInScope()
  }
}
