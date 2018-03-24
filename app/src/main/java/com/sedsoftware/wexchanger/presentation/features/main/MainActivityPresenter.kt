package com.sedsoftware.wexchanger.presentation.features.main

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.wexchanger.presentation.base.BasePresenter
import com.sedsoftware.wexchanger.presentation.navigation.AppScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter @Inject constructor(
  private val router: Router
) : BasePresenter<MainActivityView>() {

  fun onMarketTabSelected(): Boolean {
    router.replaceScreen(AppScreen.MARKET_SCREEN)
    return true
  }

  fun onOrdersTabSelected(): Boolean {
    router.replaceScreen(AppScreen.ORDERS_SCREEN)
    return true
  }

  fun onWalletTabSelected(): Boolean {
    router.replaceScreen(AppScreen.WALLET_SCREEN)
    return true
  }

  fun onTrackerTabSelected(): Boolean {
    router.replaceScreen(AppScreen.TRACKER_SCREEN)
    return true
  }

  fun onSettingsTabSelected(): Boolean {
    router.navigateTo(AppScreen.SETTINGS_SCREEN)
    return true
  }

  fun getMainRouter(): Router = router

  fun onBackPressed() {
    router.exit()
  }
}
