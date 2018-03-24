package com.sedsoftware.wexchanger.presentation.features.orders.helper

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseNestedFragment
import toothpick.Toothpick

@Layout(R.layout.fragment_orders_helper)
class OrdersHelperFragment : BaseNestedFragment(), OrdersHelperView {

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
