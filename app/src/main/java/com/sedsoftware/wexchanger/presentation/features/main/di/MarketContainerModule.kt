package com.sedsoftware.wexchanger.presentation.features.main.di

import com.sedsoftware.wexchanger.presentation.features.main.containers.market.MarketContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.di.provider.MarketNavigatorProvider
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class MarketContainerModule(fragment: MarketContainerFragment) : Module() {
  init {
    bind(Navigator::class.java).toProviderInstance(MarketNavigatorProvider(fragment))
      .providesSingletonInScope()
    bind(Router::class.java).toInstance(fragment.router)
  }
}
