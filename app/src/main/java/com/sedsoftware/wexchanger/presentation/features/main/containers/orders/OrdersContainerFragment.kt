package com.sedsoftware.wexchanger.presentation.features.main.containers.orders

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.containers.di.OrdersContainerModule
import ru.terrakok.cicerone.Navigator
import toothpick.Toothpick

@LayoutResource(R.layout.fragment_tab_container)
class OrdersContainerFragment : BaseContainerFragment(), OrdersContainerView {

  companion object {
    fun newInstance(tag: String?) = OrdersContainerFragment().apply {
      arguments = Bundle().apply {
        putString(CONTAINER_TAG_KEY, tag)
      }
    }
  }

  override val localNavigator: Navigator
    get() =
      Toothpick
        .openScope(AppScope.TAB_ORDERS)
        .getInstance(Navigator::class.java)

  @InjectPresenter
  lateinit var presenter: OrdersContainerPresenter

  @ProvidePresenter
  fun providePresenter(): OrdersContainerPresenter {
    val scope = Toothpick.openScopes(AppScope.APPLICATION, AppScope.TAB_ORDERS)
    scope.installModules(OrdersContainerModule(this))
    Toothpick.inject(this, scope)
    return scope.getInstance(OrdersContainerPresenter::class.java)
  }
}
