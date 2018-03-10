package com.sedsoftware.wexchanger.presentation.features.orders.list

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.wexchanger.presentation.base.BasePresenter
import com.sedsoftware.wexchanger.presentation.navigation.AppScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class OrdersListPresenter @Inject constructor(
  private val router: Router
) : BasePresenter<OrdersListView>() {

  fun onOrderClicked() {
    router.navigateTo(AppScreen.ORDERS_HELPER)
  }
}
