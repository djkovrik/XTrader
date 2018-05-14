package com.sedsoftware.xtrader.presentation.features.main.containers.orders

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.xtrader.di.AppScope
import com.sedsoftware.xtrader.presentation.base.BasePresenter
import com.sedsoftware.xtrader.presentation.navigation.AppScreen
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class OrdersContainerPresenter @Inject constructor(
  private val router: Router
) : BasePresenter<OrdersContainerView>() {

  override fun onFirstViewAttach() {
    super.onFirstViewAttach()
    router.replaceScreen(AppScreen.ORDERS_LIST)
  }

  override fun onDestroy() {
    Toothpick.closeScope(AppScope.TAB_ORDERS)
    super.onDestroy()
  }
}
