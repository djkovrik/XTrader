package com.sedsoftware.wexchanger.presentation.features.orders.helper

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseFragment
import toothpick.Toothpick

@LayoutResource(R.layout.fragment_orders_helper)
class OrdersHelperFragment : BaseFragment(), OrdersHelperView {

  companion object {
    fun newInstance() = OrdersHelperFragment()
  }

  @InjectPresenter
  lateinit var presenter: OrdersHelperPresenter

  @ProvidePresenter
  fun providePresenter(): OrdersHelperPresenter =
    Toothpick
      .openScope(AppScope.TAB_ORDERS)
      .getInstance(OrdersHelperPresenter::class.java)
}
