package com.sedsoftware.wexchanger.presentation.features.market.info

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.wexchanger.presentation.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MarketPairInfoPresenter @Inject constructor(
  private val router: Router
) : BasePresenter<MarketPairInfoView>() {

  fun onBackPressed() {
    router.exit()
  }
}
