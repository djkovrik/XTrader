package com.sedsoftware.wexchanger.presentation.features.main.di

import com.sedsoftware.wexchanger.presentation.features.main.containers.orders.OrdersContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.di.provider.OrdersNavigatorProvider
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class OrdersContainerModule(fragment: OrdersContainerFragment) : Module() {
  init {
    bind(Navigator::class.java).toProviderInstance(OrdersNavigatorProvider(fragment)).providesSingletonInScope()
    bind(Router::class.java).toInstance(fragment.router)
  }
}
