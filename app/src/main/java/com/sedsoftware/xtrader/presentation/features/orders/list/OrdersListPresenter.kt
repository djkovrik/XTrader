package com.sedsoftware.xtrader.presentation.features.orders.list

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.xtrader.presentation.base.BasePresenter
import com.sedsoftware.xtrader.presentation.navigation.AppScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class OrdersListPresenter @Inject constructor(
  private val router: Router
) : BasePresenter<OrdersListView>() {

  fun onOrderClicked() {
    router.navigateTo(AppScreen.ORDERS_HELPER)
  }

  fun onBackPressed() {
    router.exit()
  }
}
