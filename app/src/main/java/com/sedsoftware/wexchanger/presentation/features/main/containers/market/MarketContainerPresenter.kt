package com.sedsoftware.wexchanger.presentation.features.main.containers.market

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.wexchanger.presentation.base.BasePresenter
import com.sedsoftware.wexchanger.presentation.navigation.AppScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MarketContainerPresenter @Inject constructor(
  private val router: Router
) : BasePresenter<MarketContainerView>() {

  override fun onFirstViewAttach() {
    super.onFirstViewAttach()
    router.replaceScreen(AppScreen.MARKET_PAIRS_LIST)
  }
}
