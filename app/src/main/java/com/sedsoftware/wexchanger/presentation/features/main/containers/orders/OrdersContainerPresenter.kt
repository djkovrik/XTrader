package com.sedsoftware.wexchanger.presentation.features.main.containers.orders

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BasePresenter
import com.sedsoftware.wexchanger.presentation.navigation.AppScreen
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
