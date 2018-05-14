package com.sedsoftware.xtrader.presentation.features.main.di.provider

import com.sedsoftware.xtrader.presentation.features.main.containers.orders.OrdersContainerFragment
import com.sedsoftware.xtrader.presentation.features.main.containers.orders.OrdersContainerNavigator
import javax.inject.Provider

class OrdersNavigatorProvider(private val fragment: OrdersContainerFragment) :
  Provider<OrdersContainerNavigator> {

  override fun get(): OrdersContainerNavigator = OrdersContainerNavigator(fragment)
}
