package com.sedsoftware.xtrader.presentation.features.orders.helper

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.xtrader.R
import com.sedsoftware.xtrader.commons.annotation.Layout
import com.sedsoftware.xtrader.commons.listener.BackButtonListener
import com.sedsoftware.xtrader.di.AppScope
import com.sedsoftware.xtrader.presentation.base.BaseNestedFragment
import toothpick.Toothpick

@Layout(R.layout.fragment_orders_helper)
class OrdersHelperFragment : BaseNestedFragment(), OrdersHelperView, BackButtonListener {

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

  override fun onBackPressed(): Boolean {
    presenter.onBackPressed()
    return true
  }
}
