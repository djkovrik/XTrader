package com.sedsoftware.wexchanger.presentation.features.main.di

import com.sedsoftware.wexchanger.presentation.features.main.containers.wallet.WalletContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.di.provider.WalletNavigatorProvider
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class WalletContainerModule(fragment: WalletContainerFragment) : Module() {
  init {
    bind(Navigator::class.java).toProviderInstance(WalletNavigatorProvider(fragment)).providesSingletonInScope()
    bind(Router::class.java).toInstance(fragment.router)
  }
}
