package com.sedsoftware.xtrader.presentation.features.orders.helper

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.xtrader.presentation.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class OrdersHelperPresenter @Inject constructor(
  private val router: Router
) : BasePresenter<OrdersHelperView>() {

  fun onBackPressed() {
    router.exit()
  }
}
