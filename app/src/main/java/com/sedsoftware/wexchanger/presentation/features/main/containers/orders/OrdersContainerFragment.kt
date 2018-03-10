package com.sedsoftware.wexchanger.presentation.features.main.containers.orders

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseTabFragment
import com.sedsoftware.wexchanger.presentation.features.main.containers.di.OrdersContainerModule
import ru.terrakok.cicerone.Navigator
import toothpick.Toothpick
import javax.inject.Inject

@LayoutResource(R.layout.fragment_tab_container)
class OrdersContainerFragment : BaseTabFragment(), OrdersContainerView {

  companion object {
    fun newInstance(tag: String?) = OrdersContainerFragment().apply {
      arguments = Bundle().apply {
        putString(CONTAINER_TAG_KEY, tag)
      }
    }
  }

  @Inject
  lateinit var localNavigator: Navigator

  @InjectPresenter
  lateinit var presenter: OrdersContainerPresenter

  @ProvidePresenter
  fun providePresenter(): OrdersContainerPresenter =
    Toothpick
      .openScopes(AppScope.APPLICATION, AppScope.TAB_ORDERS)
      .also { scope -> scope.installModules(OrdersContainerModule(this)) }
      .also { scope -> Toothpick.inject(this, scope) }
      .getInstance(OrdersContainerPresenter::class.java)

  override fun onResume() {
    super.onResume()
    cicerone.navigatorHolder.setNavigator(localNavigator)
  }

  override fun onPause() {
    cicerone.navigatorHolder.removeNavigator()
    super.onPause()
  }
}
