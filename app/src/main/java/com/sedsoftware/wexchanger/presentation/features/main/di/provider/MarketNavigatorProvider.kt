package com.sedsoftware.wexchanger.presentation.features.main.di.provider

import com.sedsoftware.wexchanger.presentation.features.main.containers.market.MarketContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.containers.market.MarketContainerNavigator
import javax.inject.Provider

class MarketNavigatorProvider(private val fragment: MarketContainerFragment) : Provider<MarketContainerNavigator> {

  override fun get(): MarketContainerNavigator = MarketContainerNavigator(fragment)
}
