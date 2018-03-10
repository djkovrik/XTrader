package com.sedsoftware.wexchanger.presentation.features.main.containers.di

import com.sedsoftware.wexchanger.presentation.features.main.containers.market.MarketContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.containers.market.MarketContainerNavigator
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class MarketContainerModule(fragment: MarketContainerFragment) : Module() {
  init {
    bind(Navigator::class.java).toInstance(MarketContainerNavigator(fragment))
    bind(Router::class.java).toInstance(fragment.router)
  }
}
