package com.sedsoftware.wexchanger.presentation.features.market.list

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.wexchanger.presentation.base.BasePresenter
import com.sedsoftware.wexchanger.presentation.navigation.AppScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MarketPairsListPresenter @Inject constructor(
  private val router: Router
) : BasePresenter<MarketPairsListView>() {

  fun onPairInfoClicked() {
    router.navigateTo(AppScreen.MARKET_PAIR_INFO)
  }

  fun onBackPressed() {
    router.exit()
  }
}
