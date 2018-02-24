package com.sedsoftware.wexchanger.di.module

import com.sedsoftware.data.executor.ThreadExecutor
import com.sedsoftware.domain.executor.Executor
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class AppModule : Module() {
  init {
    //Navigation
    val cicerone = Cicerone.create()
    bind(Router::class.java).toInstance(cicerone.router)
    bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)

    // Threading
    bind(Executor::class.java).toInstance(ThreadExecutor())
  }
}
