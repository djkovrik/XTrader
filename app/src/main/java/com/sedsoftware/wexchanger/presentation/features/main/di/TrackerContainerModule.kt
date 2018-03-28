package com.sedsoftware.wexchanger.presentation.features.main.di

import com.sedsoftware.wexchanger.presentation.features.main.containers.tracker.TrackerContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.di.provider.TrackerNavigatorProvider
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class TrackerContainerModule(fragment: TrackerContainerFragment) : Module() {
  init {
    bind(Navigator::class.java).toProviderInstance(TrackerNavigatorProvider(fragment))
      .providesSingletonInScope()
    bind(Router::class.java).toInstance(fragment.router)
  }
}
