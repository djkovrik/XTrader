package com.sedsoftware.wexchanger.presentation.features.main.di.provider

import com.sedsoftware.wexchanger.presentation.features.main.containers.wallet.WalletContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.containers.wallet.WalletContainerNavigator
import javax.inject.Provider

class WalletNavigatorProvider(private val fragment: WalletContainerFragment) : Provider<WalletContainerNavigator> {

  override fun get(): WalletContainerNavigator = WalletContainerNavigator(fragment)
}
